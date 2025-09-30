package kurokishi.task;

/*
 * Inherits a task with added [T] tag
 */
public class Todo extends Task {
    
    /**
     * Creates a todo.
     *
     * @param description Task description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the display string for this todo.
     *
     * @return Display string.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }   
    
}
