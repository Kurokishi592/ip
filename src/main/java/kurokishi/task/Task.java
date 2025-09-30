package kurokishi.task;

/**
 * Represents a task with a description and completion status.
 * Superclass for all task types in the Kurokishi system.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task.
     *
     * @param description Task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the visual indicator of task completion status.
     *
     * @return "[X]" if completed, "[ ]" if not completed
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Sets completion state.
     *
     * @param isMark True to mark done; false to mark not done.
     */
    public void setDone(boolean isMark) {
        this.isDone = isMark;
    }

    /**
     * Returns the task description.
     *
     * @return Description string.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the completion state.
     *
     * @return Completion state.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Returns a user-friendly string.
     *
     * @return Display string.
     */
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
