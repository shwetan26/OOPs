package interpreter.grammar;
/**
 * @author shweta Nazarkar 
 * RED ID - 823812620
 * Email - shwetanazarkar@gmail.com
 * This class represents the subtraction operation
 */
import interpreter.interfaces.Expression;

public class SubtractionExpression implements Expression {
	private Expression leftExpresssion;
	private Expression rightExpression;

	public SubtractionExpression(Expression leftExpresssion, Expression rightExpression) {
		super();
		this.leftExpresssion = leftExpresssion;
		this.rightExpression = rightExpression;
	}

	@Override
	public double evaluate() {
		return this.leftExpresssion.evaluate() - this.rightExpression.evaluate();
	}

	@Override
	public String toString() {
		return "(" + this.leftExpresssion + "-" + this.rightExpression + ")";
	}
}
