package kurokishi.exception;

/**
 * Signals that user input is malformed or invalid.
 */
public class InputException extends Exception {
    public InputException(String message) {
        super(message);
    }
}
