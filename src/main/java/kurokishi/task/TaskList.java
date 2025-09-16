package kurokishi.task;


import kurokishi.exception.InputException;


import java.util.ArrayList;
import java.util.List;

/*
 * TaskList.java manages the tasks storage with MAX_TASKS number of tasks.
 */

public class TaskList {
    private static final int MAX_TASKS = 100;
    private final ArrayList<Task> tasks = new ArrayList<>();


    public void add(Task task) throws InputException {
        if (tasks.size() >= MAX_TASKS) {
            throw new InputException("    [SYSTEM WARNING] Memory capacity exceeded. Task list full.");
        }
        tasks.add(task);
    }


    public Task get(int index) throws InputException {
        if (index < 0 || index >= tasks.size()) {
            throw new InputException("    [ERROR] Input does not match valid task index.\n" +
                    "    [SYSTEM NOTICE] Use 'list' to view valid task numbers.");
        }
        return tasks.get(index);
    }


    public int size() {
        return tasks.size();
    }


    public List<Task> all() {
        return tasks;
    }
}
