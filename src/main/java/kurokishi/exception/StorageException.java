package kurokishi.exception;

/**
 * Indicates failure in file system operations, such as to tasks.txt file.
 */
public class StorageException extends Exception {
    
    /**
     * Creates a StorageException.
     *
     * @param message Error message.
     */
    public StorageException(String message) {
        super(message);
    }
}
