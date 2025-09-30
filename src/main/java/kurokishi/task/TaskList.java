package kurokishi.task;

import kurokishi.exception.InputException;
import kurokishi.exception.StorageException;
import kurokishi.data.Storage;

import java.util.ArrayList;
import java.util.List;

/*
 * TaskList class manages the tasks storage. It supports adding, retrieving tasks and getting the size of the list.
 * It also enforces a maximum capacity of 100 tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();
    private static final int MAX_TASKS = 100;
    private final Storage storage;

    public TaskList(Storage storage) {
        this.storage = storage;
        try {
            List<Task> loadedTasks = storage.loadFromFile();
            tasks.addAll(loadedTasks);
        } catch (StorageException e) {
            System.out.println(e.getMessage());
        }
    }

    public void add(Task task) throws InputException {
        if (tasks.size() >= MAX_TASKS) {
            throw new InputException("    [SYSTEM WARNING] Memory capacity exceeded. Task list full.");
        }
        tasks.add(task);
        try {
            storage.writeToFile(tasks);
        } catch (StorageException e) {
            System.out.println(e.getMessage());
        }
    }

    public void remove(Task task) throws InputException {
        if (tasks.size() == 0) {
            throw new InputException("    [SYSTEM WARNING] Memory is blank. Nothing to erase");
        }
        tasks.remove(task);
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

    public void saveTasks() throws StorageException {
        try {
            storage.writeToFile(tasks);
        } catch (StorageException e) {
            throw new StorageException("[ERROR] Failed to save tasks: " + e.getMessage());
        }
    }
}
