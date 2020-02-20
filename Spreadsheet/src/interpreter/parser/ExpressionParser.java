package interpreter.parser;

/**
 * @author shweta Nazarkar 
 * RED ID - 823812620
 * Email - shwetanazarkar@gmail.com
 * 
 * This class parse the input postfix expression 
 * and evaluated the expression
 */
import java.util.Stack;

import interpreter.grammar.AdditionExpression;
import interpreter.grammar.DivisionExpression;
import interpreter.grammar.LogBaseTwoExpression;
import interpreter.grammar.MultiplicationExpression;
import interpreter.grammar.NumberExpression;
import interpreter.grammar.SineExpression;
import interpreter.grammar.SubtractionExpression;
import interpreter.interfaces.Expression;

public class ExpressionParser {

    private Stack<Expression> expressionStack = new Stack<Expression>();
    private final String ADD_OPERATOR = "+";
    private final String SUB_OPERATOR = "-";
    private final String DIV_OPERATOR = "/";
    private final String MUL_OPERATOR = "*";
    private final String LOG_OPERATOR = "lg";
    private final String SINE_OPERATOR = "sin";
    private final String EMPTY_STRING = " ";

    public double parse(String input) {
        if (input.isEmpty()) {
            return 0.0;
        }
        if (input.equals(EMPTY_STRING)) {
            return 0.0;
        }
        double result = 0.0;
        double output = 0.0;
        String[] tokenList = input.split(" ");
        for (String token : tokenList) {
            if (isOperand(token)) {
                // push operand to stack
                Expression numberExpression = new NumberExpression(token);
                expressionStack.push(numberExpression);
            } else {
                // token is operator
                Expression operation;
                if (!expressionStack.isEmpty()) {
                    if (!isUnaryOperator(token)) {
                        Expression leftExpression = expressionStack.pop();
                        Expression rightExpression = expressionStack.pop();
                        operation = getExpression(leftExpression, rightExpression, token);
                    } else {
                        Expression expression = expressionStack.pop();
                        //if operator is unary pass second expression as null
                        operation = getExpression(expression, null, token);
                    }
                    result = operation.evaluate();
                    NumberExpression resultExpression = new NumberExpression(result);
                    expressionStack.push(resultExpression);
                }
            }
        }
        if (!expressionStack.isEmpty()) {
            output = expressionStack.pop().evaluate();
        }
        return output;
    }

    /**
     * This method will match token with variable of format eg."$A"
     *
     * @param token
     * @return boolean result
     */
    public boolean isVariable(String token) {
        return token != null && token.matches("^\\$[a-zA-Z]");
    }

    /**
     * This method will return true if token is operand
     *
     * @param token
     * @return boolean result
     */
    public boolean isOperand(String token) {
        return token != null && token.matches("^\\d+\\.?\\d*$");
    }

    /**
     * This method will return true if token is unary operator log,sine,!
     *
     * @param token
     * @return boolean result
     */
    public boolean isUnaryOperator(String token) {
        boolean val = token != null && token.matches("lg|sin|!");
        return val;
    }

    /**
     * This method will generate mathematical expression from
     * leftExpression,rightExpression,operator
     *
     * @param leftExpression
     * @param rightExpression
     * @param operator
     * @return expression with operator
     */
    private Expression getExpression(Expression leftExpression, Expression rightExpression, String operator) throws UnsupportedOperationException {
        switch (operator) {
            case ADD_OPERATOR:
                return new AdditionExpression(leftExpression, rightExpression);
            case SUB_OPERATOR:
                return new SubtractionExpression(leftExpression, rightExpression);
            case DIV_OPERATOR:
                return new DivisionExpression(leftExpression, rightExpression);
            case MUL_OPERATOR:
                return new MultiplicationExpression(leftExpression, rightExpression);
            case LOG_OPERATOR:
                return new LogBaseTwoExpression(leftExpression);
            case SINE_OPERATOR:
                return new SineExpression(leftExpression);
            default:
                throw new UnsupportedOperationException("Operator is not supported: " + operator);
        }
    }
}
