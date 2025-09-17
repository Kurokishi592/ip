package kurokishi.task;

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
