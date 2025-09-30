package kurokishi.command;

import kurokishi.task.TaskList;
import kurokishi.ui.Ui;
import kurokishi.exception.InputException;

/**
 * Command to exit the application.
 */
public class ExitCommand implements Command {
    
    /**
     * Signals the application to terminate.
     *
     * @param tasks Task list (unused).
     * @param ui UI handler for output.
     * @return True to request exit.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui) throws InputException {
        return true;
    }
}