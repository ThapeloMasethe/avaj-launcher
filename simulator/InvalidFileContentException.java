package simulator;

/**
 * InvalidFileContentException Class
 * 
 * @author  Thapelo MAsethe
 * @since   2019-06-30
 * @version 1.0
 */
public class InvalidFileContentException extends Exception {

    /**
     * InvalidFileContentException Constructor
     * 
     * @param message The message to be thrown.
     */
    public InvalidFileContentException(String message) {
        // Invoke exception Constructor.
        super(message);
    }
}