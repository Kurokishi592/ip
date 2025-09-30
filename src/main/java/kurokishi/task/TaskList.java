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

    /**
     * Adds a task.
     *
     * @param task Task to add.
     */
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

        /**
     * Removes a task at a 1-based index.
     *
     * @param task Task to remove.
     * @return Removed task.
     * @throws InputException If task is not found.
     */
    public void remove(Task task) throws InputException {
        if (tasks.size() == 0) {
            throw new InputException("    [SYSTEM WARNING] Memory is blank. Nothing to erase");
        }
        tasks.remove(task);
    }

    /**
     * Finds tasks whose descriptions contain a keyword (case-insensitive).
     *
     * @param keyword Keyword to search.
     * @return Matching tasks.
     */
    public List<Task> find(String keyword) {
        String k = keyword.toLowerCase();
        List<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getDescription().toLowerCase().contains(k)) {
                result.add(t);
            }
        }
        return result;
    }

    /**
     * Retrieves a task at a 0-based index.
     *
     * @param index Index of the task.
     * @return Task at the index.
     * @throws InputException If index is invalid.
     */
    public Task get(int index) throws InputException {
        if (index < 0 || index >= tasks.size()) {
            throw new InputException("    [ERROR] Input does not match valid task index.\n" +
                    "    [SYSTEM NOTICE] Use 'list' to view valid task numbers.");
        }
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks.
     *
     * @return Number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns all tasks.
     *
     * @return List of all tasks.
     */
    public List<Task> all() {
        return tasks;
    }

    /**
     * Saves the current tasks to storage.
     *
     * @throws StorageException If saving fails.
     */
    public void saveTasks() throws StorageException {
        try {
            storage.writeToFile(tasks);
        } catch (StorageException e) {
            throw new StorageException("[ERROR] Failed to save tasks: " + e.getMessage());
        }
    }
}
