package kurokishi.task;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/*
 * Inherits a task with added [D] tag  and a by (deadline)
 * Accepts both date-only (yyyy-MM-dd) and date-time (yyyy-MM-dd HHmm) formats for deadlines.
 * Displays date-only deadlines in "d MMM yyyy" format and date-time deadlines in "d MMM yyyy HHmm" format.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter DISPLAY_DATE = DateTimeFormatter.ofPattern("d MMM yyyy");
    private static final DateTimeFormatter DISPLAY_DATETIME = DateTimeFormatter.ofPattern("d MMM yyyy HHmm");
    private static final DateTimeFormatter STORE = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    public LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public LocalDateTime getBy() {
        return by;
    }

    private String formatForDisplay(LocalDateTime dt) {
        return dt.toLocalTime().equals(LocalTime.MIDNIGHT)
                ? dt.toLocalDate().format(DISPLAY_DATE)
                : dt.format(DISPLAY_DATETIME);
    }

    public String toFileString() {
        // Persist as ISO_LOCAL_DATE_TIME (e.g., 2019-10-15T00:00)
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by.format(STORE);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatForDisplay(by) + ")";
    }
}