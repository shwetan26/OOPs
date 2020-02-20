package interpreter.grammar;
/**
 * This class represents the log to base 2 unary operation
 * @author shweta Nazarkar 
 * RED ID - 823812620
 * Email - shwetanazarkar@gmail.com
 */
import interpreter.interfaces.Expression;

public class LogBaseTwoExpression implements Expression {
	private final int BASE_TWO = 2;
	private Expression expression;

	public LogBaseTwoExpression(Expression expression) {
		super();
		this.expression = expression;
	}

	@Override
	public double evaluate() {
		return (Math.log(this.expression.evaluate()) / Math.log(BASE_TWO));
	}

	// TODO tostring
}
