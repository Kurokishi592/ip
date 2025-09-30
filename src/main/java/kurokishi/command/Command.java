package kurokishi.command;

import kurokishi.task.TaskList;
import kurokishi.ui.Ui;
import kurokishi.exception.StorageException;
import kurokishi.exception.InputException;

/**
 * Represents an executable user command.
 */
public interface Command {

    /**
     * Executes the command.
     *
     * @param tasks Task list to operate on.
     * @param ui UI handler for output.
     * @return True if the application should exit; false otherwise.
     * @throws InputException If input is invalid.
     * @throws StorageException If storage access fails.
     */
    boolean execute(TaskList tasks, Ui ui) throws InputException, StorageException;
}
