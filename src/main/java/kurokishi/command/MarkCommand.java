package kurokishi.command;

import kurokishi.task.TaskList;
import kurokishi.exception.InputException;
import kurokishi.ui.Ui;

public class MarkCommand implements Command {
    private final String taskString;
    private final boolean mark;

    public MarkCommand(String taskString, boolean mark) {
        this.taskString = taskString;
        this.mark = mark;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui) throws InputException {
        if (taskString == null || taskString.trim().isEmpty()) {
            throw new InputException("    [ERROR] Please specify a task number.\n" +
                    "    [SYSTEM NOTICE] Usage: " + (mark ? "mark" : "unmark") + " <task number>");
        }
        try {
            int index = Integer.parseInt(taskString.trim()) - 1;
            tasks.get(index).setDone(mark);
            if (mark) {
                ui.printMessage("    [SYSTEM UPDATE] Task status: marked as complete.");
            } else {
                ui.printMessage("    [SYSTEM UPDATE] Task status: reverted to incomplete.");
            }
            ui.printMessage("    " + tasks.get(index));
            return false;
        } catch (NumberFormatException e) {
            throw new InputException("    [ERROR] Invalid task number format\n" +
                    "    [SYSTEM NOTICE] Task number must be an integer.");
        }
    }
}
