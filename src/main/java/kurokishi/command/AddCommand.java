package kurokishi.command;

import kurokishi.task.TaskList;
import kurokishi.task.Normal;
import kurokishi.exception.InputException;
import kurokishi.ui.Ui;

public class AddCommand implements Command {
    private final String input;

    public AddCommand(String input) { 
        this.input = input; 
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui) throws InputException {
        if (input == null || input.trim().isEmpty()) {
            throw new InputException("    [ERROR] Missing item description for 'add' command.\n" +
                    "    [SYSTEM NOTICE] Usage: add <description>");
        }
        Normal t = new Normal(input.trim());
        tasks.add(t);
        ui.printMessage("    [SYSTEM NOTICE] Item has been registered in memory: " + t);
        return false;
    }
}