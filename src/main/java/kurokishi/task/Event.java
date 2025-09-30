package kurokishi.task;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/*
 * Inherits a task with added [E] tag  and a from and to (event duration)
 * Accepts both date-only (yyyy-MM-dd) and date-time (yyyy-MM-dd HHmm) formats for event timings.
 * Displays date-only event timings in "d MMM yyyy" format and date-time event timings in "d MMM yyyy HHmm" format.
 */
public class Event extends Task{
    private static final DateTimeFormatter DISPLAY_DATE = DateTimeFormatter.ofPattern("d MMM yyyy");
    private static final DateTimeFormatter DISPLAY_DATETIME = DateTimeFormatter.ofPattern("d MMM yyyy HHmm");
    private static final DateTimeFormatter STORE = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public LocalDateTime from;
    public LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    private String formatForDisplay(LocalDateTime dt) {
        return dt.toLocalTime().equals(LocalTime.MIDNIGHT)
                ? dt.toLocalDate().format(DISPLAY_DATE)
                : dt.format(DISPLAY_DATETIME);
    }

    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description
                + " | " + from.format(STORE)
                + " | " + to.format(STORE);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + formatForDisplay(from)
                + " to: " + formatForDisplay(to) + ")";
    }
}
