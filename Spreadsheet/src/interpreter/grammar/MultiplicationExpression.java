package interpreter.grammar;
/**
 * This class represents the multiplication operation
 * @author shweta Nazarkar 
 * RED ID - 823812620
 * Email - shwetanazarkar@gmail.com
 */
import interpreter.interfaces.Expression;

public class MultiplicationExpression implements Expression {
	private Expression leftExpresssion;
	private Expression rightExpression;

	public MultiplicationExpression(Expression leftExpresssion, Expression rightExpression) {
		super();
		this.leftExpresssion = leftExpresssion;
		this.rightExpression = rightExpression;
	}

	@Override
	public double evaluate() {
		return this.leftExpresssion.evaluate() * this.rightExpression.evaluate();
	}

	@Override
	public String toString() {
		return "(" + this.leftExpresssion + "*" + this.rightExpression + ")";
	}
}
