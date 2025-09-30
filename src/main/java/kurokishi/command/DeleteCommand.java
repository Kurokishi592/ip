package kurokishi.command;

import kurokishi.task.TaskList;
import kurokishi.task.Task;
import kurokishi.exception.InputException;
import kurokishi.ui.Ui;

/**
 * Command to delete a task by index.
 */
public class DeleteCommand implements Command {
    private final String deleteString;

    /**
     * Creates a DeleteCommand.
     *
     * @param deleteString Task index as string.
     */
    public DeleteCommand(String deleteString) { 
        this.deleteString = deleteString; 
    }

    /**
     * Deletes a task at the given index.
     *
     * @param tasks Task list to modify.
     * @param ui UI handler for output.
     * @return False to continue running.
     * @throws InputException If index is invalid.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui) throws InputException {
        if (deleteString == null || deleteString.trim().isEmpty()) {
            throw new InputException("    [ERROR] Missing item description for 'delete' command.\n" +
                    "    [SYSTEM NOTICE] Usage: delete <task number>");
        }
        try {
            int index = Integer.parseInt(deleteString.trim()) - 1;
            Task t = tasks.get(index);
            tasks.remove(t);
            try {
                tasks.saveTasks();
            } catch (Exception e) {
                throw new InputException("    [ERROR] Failed to save task status: " + e.getMessage());
            }
            ui.showMessage("    [SYSTEM NOTICE] Event task deleted successfully.\n " + "         " + t);
            ui.showMessage("    [STATUS] Current number of active tasks: " + tasks.size());
        }
        catch (NumberFormatException e) {
            throw new InputException("    [ERROR] Invalid task number format\n" +
                    "    [SYSTEM NOTICE] Task number must be an integer.");
        }
        return false;
    }
}