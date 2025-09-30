package kurokishi.command;

import kurokishi.task.TaskList;
import kurokishi.task.Normal;
import kurokishi.exception.InputException;
import kurokishi.ui.Ui;

/**
 * Command to add a task.
 */
public class AddCommand implements Command {
    private final String input;

    /**
     * Creates an AddCommand.
     *
     * @param input Item description to add.
     */
    public AddCommand(String input) { 
        this.input = input; 
    }

    /**
     * Adds a task with the provided description.
     *
     * @param tasks Task list to add to.
     * @param ui UI handler for output.
     * @return False to continue running.
     * @throws InputException If description is missing.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui) throws InputException {
        if (input == null || input.trim().isEmpty()) {
            throw new InputException("    [ERROR] Missing item description for 'add' command.\n" +
                    "    [SYSTEM NOTICE] Usage: add <description>");
        }
        Normal t = new Normal(input.trim());
        tasks.add(t);
        ui.showMessage("    [SYSTEM NOTICE] Item has been registered in memory: " + t);
        return false;
    }
}