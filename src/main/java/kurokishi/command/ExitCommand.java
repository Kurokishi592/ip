package kurokishi.command;

import kurokishi.task.TaskList;
import kurokishi.ui.Ui;
import kurokishi.exception.InputException;

public class ExitCommand implements Command {
    @Override
    public boolean execute(TaskList tasks, Ui ui) throws InputException {
        return true;
    }
}