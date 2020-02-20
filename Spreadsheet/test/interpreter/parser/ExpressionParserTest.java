package interpreter.parser;

import java.text.DecimalFormat;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * @author shweta Nazarkar 
 * RED ID - 823812620 
 * Email - shwetanazarkar@gmail.com
 *
 * This class has test methods to unit test ExpressionParser class
 *
 */
public class ExpressionParserTest {

    ExpressionParser parser = new ExpressionParser();
    String input;
    String token;
    String operand;

    @Before
    public void setup() {
        input = "1967 21 + 3 sin *";
        token = "+";
        operand = "9";
    }

    @Test
    public void parseTest() {
        DecimalFormat df2 = new DecimalFormat("#.##");
        String expected = "280.55";
        String result = df2.format(parser.parse(input));
        assertEquals(expected, result);
    }

    @Test
    public void isUnaryOperatorTest() {
        assertFalse(parser.isUnaryOperator(token));
    }

    @Test
    public void isOperandTest() {
        assertTrue(parser.isOperand(operand));
    }
}
