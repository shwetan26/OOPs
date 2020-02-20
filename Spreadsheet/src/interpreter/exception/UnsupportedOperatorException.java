package interpreter.exception;

/**
 * @author shweta Nazarkar 
 * RED ID - 823812620
 * Email - shwetanazarkar@gmail.com
 * 
 * This is custom exception when unsupported operator is encountered in input
 */
public class UnsupportedOperatorException extends Exception {

    public UnsupportedOperatorException(String message) {
        super(message);
    }

}
