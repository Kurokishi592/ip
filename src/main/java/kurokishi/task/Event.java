package kurokishi.task;

/*
 * Inherits a task with added [E] tag  and a from and to (event duration)
 */

public class Event extends Task{
    public String from;
    public String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }
    
    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
