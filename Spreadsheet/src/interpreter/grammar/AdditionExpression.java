package interpreter.grammar;
/**
 * This class represents the addition expression
 * @author shweta Nazarkar 
 * RED ID - 823812620
 * Email - shwetanazarkar@gmail.com
 */
import interpreter.interfaces.Expression;

public class AdditionExpression implements Expression {
	private Expression leftExpression;
	private Expression rightExpression;

	public AdditionExpression(Expression leftExpression, Expression rightExpression) {
		super();
		this.leftExpression = leftExpression;
		this.rightExpression = rightExpression;
	}

	@Override
	public double evaluate() {
		return this.leftExpression.evaluate() + this.rightExpression.evaluate();
	}

	@Override
	public String toString() {
		return "(" + this.leftExpression.toString() + "+" + this.rightExpression.toString() + ")";
	}
}
