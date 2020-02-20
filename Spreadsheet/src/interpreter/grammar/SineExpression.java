package interpreter.grammar;
/**
 * @author shweta Nazarkar 
 * RED ID - 823812620
 * Email - shwetanazarkar@gmail.com
 * 
 * This class represents the Sine operation
 */
import interpreter.interfaces.Expression;

public class SineExpression implements Expression {
	private Expression expression;

	public SineExpression(Expression expression) {
		super();
		this.expression = expression;
	}

	@Override
	public double evaluate() {
		return Math.sin(this.expression.evaluate());
	}

}
