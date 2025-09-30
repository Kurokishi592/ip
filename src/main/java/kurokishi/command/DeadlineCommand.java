package kurokishi.command;

import kurokishi.task.TaskList;
import kurokishi.task.Deadline;
import kurokishi.exception.InputException;
import kurokishi.ui.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/*
 * Command to add a deadline task.
 * Parses args and creates a Deadline with date or date-time.
 * Accepted inputs: yyyy-MM-dd or yyyy-MM-dd HHmm after '/by'.
 */
public class DeadlineCommand implements Command {
    private final String deadlineString;
    private static final DateTimeFormatter DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Creates a DeadlineCommand.
     *
     * @param deadlineString Raw argument string after the keyword.
     */
    public DeadlineCommand(String deadlineString) { 
        this.deadlineString = deadlineString; 
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
            // Try full date-time
            return LocalDateTime.parse(s, DATE_TIME);
        } catch (DateTimeParseException ignored) {
            try {
                // Fallback to date only => 00:00
                LocalDate d = LocalDate.parse(s, DateTimeFormatter.ISO_LOCAL_DATE);
                return d.atStartOfDay();
            } catch (DateTimeParseException e) {
                throw new InputException("    [ERROR] Invalid date format for deadline.\n" +
                        "    [SYSTEM NOTICE] Please use the format: yyyy-MM-dd or yyyy-MM-dd HHmm (e.g., 2019-10-15 1800).");
            }
        }
    }

    /**
     * Parses arguments and adds a deadline.
     *
     * @param tasks Task list to add to.
     * @param ui UI handler for output.
     * @return False to continue running.
     * @throws InputException If arguments are missing or invalid.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui) throws InputException {
        String[] deadlineParts = deadlineString.split(" /by ", 2);
        if (deadlineParts.length < 2 || deadlineString == null || !deadlineString.contains("/by") || 
                deadlineParts[0].trim().isEmpty() || deadlineParts[1].trim().isEmpty()) {
            throw new InputException("    [ERROR] Please specify a deadline description and time using '/by' and correct format.\n" +
                    "    [SYSTEM NOTICE] Usage: deadline <description> /by <yyyy-MM-dd[ HHmm]>");
        }
        LocalDateTime by = parseFlexibleDateTime(deadlineParts[1].trim());
        Deadline deadlineTask = new Deadline(deadlineParts[0].trim(), by);
        tasks.add(deadlineTask);
        ui.showMessage("    [SYSTEM NOTICE] Deadline task added successfully.\n " + "         " + deadlineTask);
        ui.showMessage("    [STATUS] Current number of active tasks: " + tasks.size());
        return false;
    }
}