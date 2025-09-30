package kurokishi.command;

import kurokishi.task.TaskList;
import kurokishi.exception.InputException;
import kurokishi.ui.Ui;

/**
 * Command to mark or unmark a task.
 */
public class MarkCommand implements Command {
    private final String taskString;
    private final boolean mark;

    /**
     * Creates a MarkCommand.
     *
     * @param taskString Task index as string.
     * @param mark True to mark done; false to unmark.
     */
    public MarkCommand(String taskString, boolean mark) {
        this.taskString = taskString;
        this.mark = mark;
    }

    /**
     * Marks/unmarks a task by index.
     *
     * @param tasks Task list to modify.
     * @param ui UI handler for output.
     * @return False to continue running.
     * @throws InputException If index is invalid.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui) throws InputException {
        if (taskString == null || taskString.trim().isEmpty()) {
            throw new InputException("    [ERROR] Please specify a task number.\n" +
                    "    [SYSTEM NOTICE] Usage: " + (mark ? "mark" : "unmark") + " <task number>");
        }
        try {
            int index = Integer.parseInt(taskString.trim()) - 1;
            tasks.get(index).setDone(mark);
            try {
                tasks.saveTasks();
            } catch (Exception e) {
                throw new InputException("    [ERROR] Failed to save task status: " + e.getMessage());
            }
            if (mark) {
                ui.showMessage("    [SYSTEM UPDATE] Task status: marked as complete.");
            } else {
                ui.showMessage("    [SYSTEM UPDATE] Task status: reverted to incomplete.");
            }
            ui.showMessage("    " + tasks.get(index));
            return false;
        } catch (NumberFormatException e) {
            throw new InputException("    [ERROR] Invalid task number format\n" +
                    "    [SYSTEM NOTICE] Task number must be an integer.");
        }
    }
}
