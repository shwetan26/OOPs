package spreadsheet;

import interpreter.parser.ExpressionParser;
import java.util.List;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import spreadsheet.exception.CyclicDependencyException;

/**
 * @author shweta Nazarkar 
 * RED ID - 823812620
 * Email - shwetanazarkar@gmail.com
 * 
 * This class represents the model of spreadsheet which performs the operations
 * on cells to set and fetch the data It maintains editHistory stack to maintain
 * the history of edited cells
 *
 */
public class SpreadSheetModel extends AbstractTableModel {

    private String[] columnNames = {"$A", "$B", "$C", "$D", "$E", "$F", "$G", "$H", "$I"};
    private Cell[][] cells;
    private ViewState state;
    private static final String EMPTY_STRING = " ";
    private Stack<Integer> editHistory = new Stack();
    private SpreadSheetView view;

    public SpreadSheetModel(Cell[][] cells) {
        this.cells = cells;
        for (int i = 0; i < cells[0].length; i++) {
            cells[0][i].setColumnLabel(columnNames[i]);
        }
    }

    public ViewState getState() {
        return state;
    }

    public void setState(ViewState state) {
        this.state = state;
    }

    public SpreadSheetView getView() {
        return view;
    }

    public void setView(SpreadSheetView view) {
        this.view = view;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return cells.length;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    /**
     * This method will return the value of cell at given row and col
     *
     * @param row
     * @param col
     * @return value of cell
     */
    @Override
    public Object getValueAt(int row, int col) {
        if (state instanceof EquationView) {
            //if cell has formula, display formula as it is in equation view
            if (!cells[row][col].getExpression().equals(EMPTY_STRING)) {
                return cells[row][col].getExpression();
            } else {
                //cell doesn't have formula, display value of cell
                return cells[row][col].getValue();
            }
        }
        //for value view, display value
        return cells[row][col].getValue();
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return true;
    }

    /**
     * This method sets the value of cell at given row and col
     *
     * @param value
     * @param row
     * @param col
     */
    @Override
    public void setValueAt(Object value, int row, int col) {
        //if state is equation detect dependency and update subject list
        String columnName = getColumnName(col);
        cells[row][col].setColumnLabel(columnName);
        if (state instanceof EquationView) {
            //find out the subjects from formula
            findSubjects(value, row, col);
            //detect if cycle is present in spreadsheet
            boolean[] visitedColumns = new boolean[getColumnCount()];
            boolean[] recStack = new boolean[getColumnCount()];
            try {
                boolean isCyclic = isCyclic(row, col, visitedColumns, recStack);
            } catch (CyclicDependencyException ex) {
                Logger.getLogger(SpreadSheetModel.class.getName()).log(Level.SEVERE, null, ex);
                view.showDialogBox(ex.getMessage(),"ERROR");
            }
            cells[row][col].setExpression((String) value);
            //evaluate the formula and set result in cell
            double val = cells[row][col].evaluate();
            cells[row][col].setValue(String.valueOf(val));
        }
        state.setValue(cells[row][col], value);
        //push column of cell edited recently
        editHistory.push(col);
        //push value of cell edited recently to stack of cell
        cells[row][col].getValueStack().push(value);
        fireTableCellUpdated(row, col);
    }

    /**
     * This method will find the Cell matching with column
     *
     * @param column
     * @param row
     * @param col
     * @return Cell
     */
    private Cell getCellFromColumn(String column, int row, int col) {
        for (int index = 0; index < cells[row].length; index++) {
            if (getColumnName(index).equals(column) && index != col) {
                return cells[row][index];
            }
        }
        return null;
    }

    /**
     * This method will find column number from given column name
     *
     * @param column
     * @return
     */
    private int findColumnFromName(String column) {
        int firstRow = 0;
        for (int index = 0; index < cells[0].length; index++) {
            if (cells[firstRow][index].getColumnLabel().equals(column)) {
                return index;
            }
        }
        return -1;
    }

    /**
     * This method will undo the recent editing of cells find out the recent
     * column edited from history stack find the cell stack from given column
     * get the recent value from cell stack
     */
    public void undo() {
        if (!editHistory.isEmpty()) {
            int recentColumn = editHistory.pop();
            //get recently edited cell from recently edited column
            Cell recentCell = getCellFromColumn(recentColumn);
            if (recentCell != null) {
                //get value stack of recently edited cell
                Stack cellValuesStack = recentCell.getValueStack();
                Object currentVal = cellValuesStack.pop();
                if (cellValuesStack.size() >= 1) {
                    Object prevValue = cellValuesStack.pop();
                    state.setValue(recentCell, prevValue);
                } else if (cellValuesStack.isEmpty()) {
                    state.setValue(recentCell, EMPTY_STRING);
                }
            }
        }
    }

    /**
     * This method will find the Cell from given column number
     *
     * @param col
     * @return Cell
     */
    private Cell getCellFromColumn(int col) {
        for (int index = 0; index < cells[0].length; index++) {
            if (index == col) {
                return cells[0][index];
            }
        }
        return null;
    }

    /**
     * This method will find subjects from given expression of cell
     *
     * @param value expression of cell
     * @param row row of cell
     * @param col column of cell
     */
    private void findSubjects(Object value, int row, int col) {
        String expression = (String) value;
        ExpressionParser parser = new ExpressionParser();
        String[] tokens = expression.split(EMPTY_STRING);
        String column = null;
        Cell subject = null;
        for (String token : tokens) {
            if (parser.isVariable(token)) {
                for (int index = 0; index < cells[row].length; index++) {
                    if (getColumnName(index).equals(token) && index != col) {
                        column = getColumnName(index);
                        subject = cells[row][index];
                        break;
                    }
                }
                //add subjects to the list of subjects of cell
                cells[row][col].addSubject(subject);
            }
        }
    }

    /**
     * This method finds direct or indirect cyclic references in formula of cell
     *
     * @param row
     * @param col
     * @param visitedColumns
     * @param recStack
     * @return true if cyclic reference is detected
     * @throws CyclicDependencyException
     */
    public boolean isCyclic(int row, int col, boolean[] visitedColumns, boolean[] recStack) throws CyclicDependencyException {
        if (recStack[col] == true) {
            throw new CyclicDependencyException("There are one or more references where a formula refers to its own cell directly or indirectly.Try removing these reference or moving formulas.");
        }
        if (visitedColumns[col] == true) {
            return false;
        }
        recStack[col] = true;
        visitedColumns[col] = true;
        List<Cell> subjects = cells[row][col].getSubjects();
        if (subjects != null && !subjects.isEmpty()) {
            for (Cell cell : subjects) {
                int column = findColumnFromName(cell.getColumnLabel());
                return isCyclic(row, column, visitedColumns, recStack);
            }
        }
        recStack[col] = false;
        return false;
    }

}
