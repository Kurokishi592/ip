package kurokishi.command;

import kurokishi.task.TaskList;
import kurokishi.task.Event;
import kurokishi.exception.InputException;
import kurokishi.ui.Ui;

public class EventCommand implements Command {
    private final String eventString;

    public EventCommand(String eventString) { 
        this.eventString = eventString; 
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui) throws InputException {
        if (eventString == null || !eventString.contains(" /from ") || !eventString.contains(" /to ")) {
            throw new InputException("    [ERROR] Please specify an event description, start time using '/from' and end time using '/to'.\n" +
                    "    [SYSTEM NOTICE] Usage: event <description> /from <start> /to <end>");
        }
        String[] eventParts1 = eventString.split(" /from ", 2);
        String[] eventParts2 = eventParts1[1].split(" /to ", 2);
        Event eventTask = new Event(eventParts1[0].trim(), eventParts2[0].trim(), eventParts2[1].trim());
        tasks.add(eventTask);
        ui.showMessage("    [SYSTEM NOTICE] Event task added successfully.\n " + "         " + eventTask);
        ui.showMessage("    [STATUS] Current number of active tasks: " + tasks.size());
        return false;
    }
}
