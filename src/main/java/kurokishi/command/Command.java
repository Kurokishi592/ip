package kurokishi.command;

import kurokishi.task.TaskList;
import kurokishi.ui.Ui;
import kurokishi.exception.InputException;

public interface Command {
    /**
    * Execute the command.
    * @return true if this command should terminate the application.
    */
    boolean execute(TaskList tasks, Ui ui) throws InputException;
}
