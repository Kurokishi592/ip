package kurokishi.task;

/*
 * Inherits a task with added [E] tag  and a from and to (event duration)
 */

public class Normal extends Task{
    public Normal(String description) {
        super(description);
    }
    
    @Override
    public String toString() {
        return "[N]" + super.toString();
    }
}
