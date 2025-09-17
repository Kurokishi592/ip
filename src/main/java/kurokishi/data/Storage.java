package kurokishi.data;

import kurokishi.task.*;
import kurokishi.exception.*;

import java.io.File;
import java.io.FileReader;
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
    public List<Task> loadFromFile() throws InputException {
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
    public void save(List<Task> tasks) throws InputException {
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

    /** Convert file line into corresponding Task object */
    private Task parseLine(String line) throws InputException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            throw new InputException("[ERROR] Invalid task format: " + line);
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
                    throw new InputException("[ERROR] Invalid deadline format: " + line);
                }
                Deadline d = new Deadline(description, parts[3]);
                d.setDone(done);
                return d;
            case "E":
                if (parts.length != 4) {
                    throw new InputException("[ERROR] Invalid event format: " + line);
                }
                // format for time is from: time to: time, need to split to get time
                if (!parts[3].contains("from:") || !parts[3].contains("to:")) {
                    throw new InputException("[ERROR] Invalid event time format: " + line);
                }
                String[] fromTime = parts[3].split("from:");
                String[] toTime = fromTime[1].split("to:");
                Event e = new Event(description, fromTime[1].trim(), toTime[1].trim());
                e.setDone(done);
                return e;
            default:
                throw new InputException("[ERROR] Unknown task type: " + type);
        }
    }

    /** Convert Task object into a file line */
    private String serializeTask(Task t) {
        String done = t.isDone() ? "1" : "0";
        if (t instanceof Todo) return "T | " + done + " | " + t.getDescription();
        else if (t instanceof Deadline) {
            Deadline d = (Deadline) t;
            return "D | " + done + " | " + d.getDescription() + " | " + d.getBy();
        } else if (t instanceof Event) {
            Event e = (Event) t;
            return "E | " + done + " | " + e.getDescription() + " | " + e.getFrom() + " - " + e.getTo();
        } else return "T | " + done + " | " + t.getDescription(); // fallback
    }
}
