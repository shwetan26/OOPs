package spreadsheet.exception;

/**
 * @author shweta Nazarkar 
 * RED ID - 823812620
 * Email - shwetanazarkar@gmail.com
 * 
 * This is custom exception thrown when cyclic dependency is detected
 */
public class CyclicDependencyException extends Exception{

    public CyclicDependencyException(String message) {
        super(message);
    }
    
}
