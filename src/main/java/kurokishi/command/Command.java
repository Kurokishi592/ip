package kurokishi.command;

import kurokishi.task.TaskList;
import kurokishi.ui.Ui;
import kurokishi.exception.InputException;

/**
 * Represents an executable command in the Kurokishi task management system.
 * All user actions are specified using Command interface for processing.
 */
public interface Command {
    /**
    * Execute the command.
    *
    * @param tasks The task list to operate on
    * @param ui The user interface for displaying output
    * @return true if this command should terminate the application.
    * @throws InputException if command parameters are invalid
    */
    boolean execute(TaskList tasks, Ui ui) throws InputException;
}
