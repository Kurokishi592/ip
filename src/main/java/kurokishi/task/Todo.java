package kurokishi.task;

/*
 * Inherits a task with added [T] tag
 */

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }   
    
}
