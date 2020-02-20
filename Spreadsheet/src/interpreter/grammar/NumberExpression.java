package interpreter.grammar;
/**
 * @author shweta Nazarkar 
 * RED ID - 823812620
 * Email - shwetanazarkar@gmail.com
 * 
 * This class represents the number expression
 */
import interpreter.interfaces.Expression;

public class NumberExpression implements Expression {
	private double number;

	public NumberExpression(double number) {
		super();
		this.number = number;
	}
	
	public NumberExpression(String token) {
		this.number = Double.parseDouble(token);
	}
	
	@Override
	public double evaluate() {
		return number;
	}

}
