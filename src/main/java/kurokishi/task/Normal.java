package kurokishi.task;

/**
 * Generic task type with description only.
 */
public class Normal extends Task{
    
    /**
     * Creates a normal task.
     *
     * @param description Task description.
     */
    public Normal(String description) {
        super(description);
    }
    
    /**
     * Returns the storage string for this task.
     *
     * @return Encoded string.
     */
    @Override
    public String toString() {
        return "[N]" + super.toString();
    }
}
