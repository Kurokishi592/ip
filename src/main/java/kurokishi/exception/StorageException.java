package kurokishi.exception;

/**
 * Indicates failure in file system operations, such as to tasks.txt file.
 */
public class StorageException extends Exception {
    public StorageException(String message) {
        super(message);
    }
}
