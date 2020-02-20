package spreadsheet;

import interpreter.parser.ExpressionParser;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Stack;

/**
 * @author shweta Nazarkar 
 * RED ID - 823812620
 * Email - shwetanazarkar@gmail.com
 * 
 * This class represents the Cell of spreadsheet Each cell will
 * behave as object as well as subject
 */
public class Cell extends Observable implements Observer {

    private String value = " ";
    private String expression = " ";
    private List<Cell> subjects = new ArrayList<>();
    private String numericalExpression;
    private String columnLabel;
    private final String SPACE_STRING = " ";
    private Stack<Object> valueStack = new Stack();

    public Cell() {
    }

    public Cell(String value, String expression) {
        this.value = value;
        this.expression = expression;
    }

    public String getNumericalExpression() {
        return numericalExpression;
    }

    public void setNumericalExpression(String numericalExpression) {
        this.numericalExpression = numericalExpression;
    }

    public List<Cell> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Cell> subjects) {
        this.subjects = subjects;
    }

    public String getColumnLabel() {
        return columnLabel;
    }

    public void setColumnLabel(String columnLabel) {
        this.columnLabel = columnLabel;
    }

    public Stack<Object> getValueStack() {
        return valueStack;
    }

    public void setValueStack(Stack<Object> valueStack) {
        this.valueStack = valueStack;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public void addSubject(Cell subject) {
        subject.addObserver(this);
        this.subjects.add(subject);
    }

    //when cell value is changed, notify its observers about changes
    public void setValue(String value) {
        this.value = value;
        setChanged();
        notifyObservers();
    }

    //when cell expression is changed, notify its observers about changes
    public void setFormula(String expression) {
        this.expression = expression;
        setChanged();
        notifyObservers();
    }

    //evaluate the expressiona and set value in cell
    public void setValue() {
        if (expression != null && !expression.isEmpty()) {
            setValue(String.valueOf(evaluate()));
        }
    }

    //fetch value from cell
    public Object getValue() {
        return value;
    }

    //when subject is changed this method will be called by observer
    @Override
    public void update(Observable o, Object arg) {
        setValue();
    }

    /**
     * This method will evaluate the cell formula and return the result
     * @return expression evaluation result 
     */
    public double evaluate() {
        double result = 0.0;
        ExpressionParser parser = new ExpressionParser();
        //first convert column variables to values and pass this expression to interpreter
        String numericalExpression = findNumericalExpression(expression);
        result = parser.parse(numericalExpression);
        return result;
    }

    /**
     * This method will find the equivalent numerical form of cell formula
     * @param expression
     * @return numerical form of expression
     */
    private String findNumericalExpression(String expression) {
        StringBuilder numericExp = new StringBuilder();
        ExpressionParser parser = new ExpressionParser();
        String[] tokens = expression.split(SPACE_STRING);
        for (String token : tokens) {
            if (parser.isVariable(token)) {
                Cell subject = findSubjectCell(token);
                if (subject != null) {
                    numericExp.append(subject.getValue());
                }
            } else {
                numericExp.append(token);
            }
            numericExp.append(SPACE_STRING);
        }
        return numericExp.toString();
    }

    /**
     * This method will find the subject cell from given column name
     * @param columnName
     * @return Cell
     */
    private Cell findSubjectCell(String columnName) {
        for (int index= 0; index < subjects.size(); index++) {
            if (columnName.equals(subjects.get(index).getColumnLabel())) {
                return subjects.get(index);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Cell{" + "value=" + value + ", expression=" + expression + ", subjects=" + subjects + '}';
    }

}
