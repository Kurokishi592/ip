package kurokishi.data;

import kurokishi.task.*;
import kurokishi.exception.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Save.java save the tasks in the hard disk automaitically whenever the task list changes
 * and load the tasks from the hard disk when the program starts
 */

public class Storage {

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /** Load tasks from the file */
    public List<Task> loadFromFile() throws StorageException {
        List<Task> tasks = new ArrayList<>();
        File f = new File(filePath); // create a File for the given file path

        try {
            if (!f.exists()) {
                // make sure parent directory exists
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

    /** Save all tasks to file (overwrite) */
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

    /** Convert file line into corresponding Task object and check if file is currupted */
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
                Deadline d = new Deadline(description, parts[3]);
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
                Event e = new Event(description, timeRange[0].trim(), timeRange[1].trim());
                e.setDone(done);
                return e;
            default:
                throw new StorageException("[ERROR] Corrupted. Unknown task type: " + type);
        }
    }

    /** Convert Task object into a file line */
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
