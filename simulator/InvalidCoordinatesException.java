package simulator;

/**
 * InvalidCoordinatesException Class
 * 
 * @author  Thapelo Masethe
 * @since   2019-06-30
 * @version 1.0
 */
public class InvalidCoordinatesException extends Exception {

    /**
     * InvalidCoordinatesException Constructor
     * 
     * @param message The message to be thrown.
     */
    public InvalidCoordinatesException(String message) {
        // Invoke Exception Constructor.
        super(message);
    }
}