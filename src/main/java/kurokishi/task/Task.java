package kurokishi.task;

/**
 * Represents a task with a description and completion status.
 * Superclass for all task types in the Kurokishi system.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the visual indicator of task completion status.
     * 
     * @return "X" if completed, " " if not completed
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public void setDone(boolean isMark) {
        this.isDone = isMark;
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
