package kurokishi.data;

import kurokishi.task.*;
import kurokishi.exception.StorageException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.time.LocalDateTime;

/*
 * Storage class saves the tasks in the hard disk automatically whenever the task list changes
 * and load the tasks from the hard disk when the program starts
 */
public class Storage {

    private final String filePath;

    /**
     * Creates a Storage bound to a file path.
     *
     * @param filePath Path of the data file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }   

    /**
     * Loads tasks from the file system.
     *
     * @return List of tasks loaded.
     * @throws StorageException If reading or parsing fails.
     */
    public List<Task> loadFromFile() throws StorageException {
        List<Task> tasks = new ArrayList<>();
        File f = new File(filePath); // create a File for the given file path

        try {
            if (!f.exists()) {
                // create parent directory if it doesn't exist
                File parent = f.getParentFile();
                if (parent != null) {
                    parent.mkdirs();
                }
                f.createNewFile();
                return tasks; // empty list
            }

            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNextLine()) {
                String line = s.nextLine();
                tasks.add(parseLine(line));
            }
            s.close();
        } catch(IOException e) {
            throw new StorageException("[ERROR] Failed to read file: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Save all tasks to file (overwrite)
     *
     * @param tasks List of tasks to save.
     * @throws StorageException If writing fails.
     */
    public void writeToFile(List<Task> tasks) throws StorageException {
        try {
            FileWriter writer = new FileWriter(filePath); // overwrite
            for (Task t : tasks) {
                writer.write(serializeTask(t) + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            throw new StorageException("[ERROR] Failed to save tasks: " + e.getMessage());
        }
    }

    /**
     * Convert file line into corresponding Task object and check if file is currupted
     * 
     * @param line Line from the data file.
     * @return Corresponding Task object.
     */
    private Task parseLine(String line) throws StorageException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            throw new StorageException("[ERROR] Corrupted. Invalid task format: " + line);
        }

        String type = parts[0];
        boolean done = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
            case "N":
                Normal normal = new Normal(description);
                normal.setDone(done);
                return normal;
            case "T":
                Task todo = new Todo(description);
                todo.setDone(done);
                return todo;
            case "D":
                if (parts.length != 4) {
                    throw new StorageException("[ERROR] Corrupted. Invalid deadline format: " + line);
                }
                LocalDateTime by = LocalDateTime.parse(parts[3]);
                Deadline d = new Deadline(description, by);
                d.setDone(done);
                return d;
            case "E":
                if (parts.length != 4) {
                    throw new StorageException("[ERROR] Corrupted. Invalid event format: " + line);
                }
                // format for time in .txt is time - time
                if (!parts[3].contains("-")) {
                    throw new StorageException("[ERROR] Corrupted. Invalid event time format: " + line);
                }
                String[] timeRange = parts[3].split(" - ");
                if (timeRange.length != 2) {
                    throw new StorageException("[ERROR] Corrupted. Invalid event time format: " + line);
                }
                LocalDateTime from = LocalDateTime.parse(timeRange[0].trim());
                LocalDateTime to = LocalDateTime.parse(timeRange[1].trim());
                Event e = new Event(description, from, to);
                e.setDone(done);
                return e;
            default:
                throw new StorageException("[ERROR] Corrupted. Unknown task type: " + type);
        }
    }

    /** 
     * Convert Task object into a file line
     *
     * @param t Task object to serialize.
     * @return String representation for file storage.
     */
    private String serializeTask(Task t) {
        String done = t.getIsDone() ? "1" : "0";
        if (t instanceof Todo) {
            return "T | " + done + " | " + t.getDescription();
        } else if (t instanceof Deadline) {
            Deadline d = (Deadline) t;
            return "D | " + done + " | " + d.getDescription() + " | " + d.getBy();
        } else if (t instanceof Event) {
            Event e = (Event) t;
            return "E | " + done + " | " + e.getDescription() + " | " + e.getFrom() + " - " + e.getTo();
        } else {
            return "N | " + done + " | " + t.getDescription();
        }
    }
}
