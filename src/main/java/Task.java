/**
 * Represents a task with a description and completion status.
 */

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // return tick or X symbols
    }

    public void setDone(boolean status) {
        this.isDone = status;
    }
}
