package kurokishi.command;

import kurokishi.task.TaskList;
import kurokishi.task.Event;
import kurokishi.exception.InputException;
import kurokishi.ui.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/*
 * Command to add an event task with start and end times.
 * Parses args and creates an Event with date or date-time for from/to.
 * Accepted inputs: yyyy-MM-dd or yyyy-MM-dd HHmm after '/from' and '/to'.
 */
public class EventCommand implements Command {
    private final String eventString;
    private static final DateTimeFormatter DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Creates an EventCommand.
     *
     * @param eventString Raw argument string after the keyword.
     */
    public EventCommand(String eventString) { 
        this.eventString = eventString; 
    }

    /**
     * Parses a date or date-time string.
     *
     * @param s Input string (yyyy-MM-dd or yyyy-MM-dd HHmm).
     * @return Parsed LocalDateTime (date-only maps to midnight).
     * @throws InputException If parsing fails.
     */
    private LocalDateTime parseFlexibleDateTime(String s) throws InputException {
        try {
            return LocalDateTime.parse(s, DATE_TIME);
        } catch (DateTimeParseException ignored) {
            try {
                LocalDate d = LocalDate.parse(s, java.time.format.DateTimeFormatter.ISO_LOCAL_DATE);
                return d.atStartOfDay();
            } catch (DateTimeParseException e) {
            throw new InputException("    [ERROR] Invalid date format for event.\n" +
                    "    [SYSTEM NOTICE] Please use the format: yyyy-MM-dd or yyyy-MM-dd HHmm (e.g., 2019-10-15 0930).");
            }
        }
    }

    /**
     * Parses arguments and adds an event.
     *
     * @param tasks Task list to add to.
     * @param ui UI handler for output.
     * @return False to continue running.
     * @throws InputException If arguments are missing or invalid.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui) throws InputException {
        if (eventString == null || eventString.trim().isEmpty()) {
            throw new InputException("    [ERROR] Please specify an event description, start time using '/from' and end time using '/to'.\n" +
                    "    [SYSTEM NOTICE] Usage: event <description> /from <yyyy-MM-dd>[ HHmm] /to <yyyy-MM-dd>[ HHmm]");
        }
        String raw = eventString.trim();
        if (!raw.contains(" /from ") || !raw.contains(" /to ")) {
            throw new InputException("    [ERROR] Please specify an event description, start time using '/from' and end time using '/to'.\n" +
                    "    [SYSTEM NOTICE] Usage: event <description> /from <yyyy-MM-dd>[ HHmm] /to <yyyy-MM-dd>[ HHmm]");
        }
        String[] eventParts1 = raw.split(" /from ", 2);
        if (eventParts1.length < 2 || eventParts1[0].trim().isEmpty()) {
            throw new InputException("    [ERROR] Event description cannot be empty.");
        }

        String[] eventParts2 = eventParts1[1].split(" /to ", 2);
        if (eventParts2.length < 2 || eventParts2[0].trim().isEmpty() || eventParts2[1].trim().isEmpty()) {
            throw new InputException("    [ERROR] Start/end time cannot be empty.\n" +
                    "    [SYSTEM NOTICE] Please use: yyyy-MM-dd or yyyy-MM-dd HHmm (e.g., 2019-10-15 0930).");
        }
        
        LocalDateTime from = parseFlexibleDateTime(eventParts2[0].trim()); // from part
        LocalDateTime to = parseFlexibleDateTime(eventParts2[1].trim());   // to part
        if (to.isBefore(from)) {
            throw new InputException("    [ERROR] End datetime cannot be before start datetime.");
        }

        Event eventTask = new Event(eventParts1[0].trim(), from, to);
        tasks.add(eventTask);
        ui.showMessage("    [SYSTEM NOTICE] Event task added successfully.\n " + "         " + eventTask);
        ui.showMessage("    [STATUS] Current number of active tasks: " + tasks.size());
        return false;   
    }
}

