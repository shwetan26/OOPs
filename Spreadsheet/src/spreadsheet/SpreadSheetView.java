package spreadsheet;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author shwet
 *
 * This class will hold the two views of spreadsheet value view and equation
 * view the state of the spreadsheet will represent the current view of it
 */
public class SpreadSheetView implements ActionListener {

    private Cell[][] cells;
    private ViewState viewState;
    private JFrame valueFrame;
    private JFrame equationFrame;
    private SpreadSheetModel model;
    private final String UNDO = "Undo";
    private final String TOGGLE = "Toggle";
    private final String EQUATION_VIEW = "Equation View";
    private final String VALUE_VIEW = "Value View";
    
    public ViewState getViewState() {
        return viewState;
    }

    public void setViewState(ViewState viewState) {
        this.viewState = viewState;
    }

    public SpreadSheetView(ViewState state, Cell[][] cells) {
        this.viewState = state;
        this.cells = cells;
    }

    public SpreadSheetModel getModel() {
        return model;
    }

    public void setModel(SpreadSheetModel model) {
        this.model = model;
    }

    public void createEquationGUI(Cell[][] cells) {
        equationFrame = new JFrame();
        equationFrame.setTitle(EQUATION_VIEW);
        model = new SpreadSheetModel(cells);
        model.setView(this);
        createTableView(equationFrame, model);
    }

    public void createValueGUI(Cell[][] cells) {
        valueFrame = new JFrame();
        valueFrame.setTitle(VALUE_VIEW);
        model = new SpreadSheetModel(cells);
        model.setView(this);
        createTableView(valueFrame, model);
    }

    public void createTableView(JFrame frame, SpreadSheetModel model) {
        JTable table = new JTable();
        model.setState(viewState);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setModel(model);
        JPanel topPanel = new JPanel();
        JPanel botPanel = new JPanel();
        JButton toggelButton = new JButton(TOGGLE);
        toggelButton.addActionListener(this);
        JButton undoButton = new JButton(UNDO);
        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.undo();
            }
        });
        frame.getContentPane().add(topPanel, BorderLayout.CENTER);
        frame.getContentPane().add(botPanel, BorderLayout.SOUTH);
        topPanel.add(scrollPane, BorderLayout.CENTER);
        botPanel.add(toggelButton);
        botPanel.add(undoButton);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        clickToggelButton();
    }

    public void clickToggelButton() {
        viewState.clickToggleButton(this);
        if (valueFrame.isVisible()) {
            valueFrame.setVisible(false);
            valueFrame.dispose();
            createEquationGUI(cells);
        } else {
            equationFrame.setVisible(false);
            equationFrame.dispose();
            createValueGUI(cells);
        }
    }

    public void showDialogBox(String infoMessage,String title){
        JOptionPane.showMessageDialog(null, infoMessage, title, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void main(String[] args) {
        Cell[][] cells = new Cell[1][9];
        for (int i = 0; i < 9; i++) {
            cells[0][i] = new Cell();
        }
        ValueView initialState = new ValueView();
        SpreadSheetView sheet = new SpreadSheetView(initialState, cells);
        sheet.createValueGUI(cells);
    }

}
