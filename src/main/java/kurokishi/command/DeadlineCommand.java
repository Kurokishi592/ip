package kurokishi.command;

import kurokishi.task.TaskList;
import kurokishi.task.Deadline;
import kurokishi.exception.InputException;
import kurokishi.ui.Ui;

public class DeadlineCommand implements Command {
    private final String deadlineString;

    public DeadlineCommand(String deadlineString) { 
        this.deadlineString = deadlineString; 
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui) throws InputException {
        if (deadlineString == null || !deadlineString.contains("/by")) {
            throw new InputException("    [ERROR] Please specify a deadline description and time using '/by'.\n" +
                    "    [SYSTEM NOTICE] Usage: deadline <description> /by <date/time>");
        }
        String[] deadlineParts = deadlineString.split(" /by ", 2);
        Deadline deadlineTask = new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim());
        tasks.add(deadlineTask);
        ui.showMessage("    [SYSTEM NOTICE] Deadline task added successfully.\n " + "         " + deadlineTask);
        ui.showMessage("    [STATUS] Current number of active tasks: " + tasks.size());
        return false;
    }
}