package kurokishi;

import kurokishi.ui.Ui;
import kurokishi.task.TaskList;
import kurokishi.parser.Parser;
import kurokishi.command.Command;
import kurokishi.exception.InputException;
import kurokishi.exception.StorageException;
import kurokishi.data.Storage;

import java.util.Scanner;


/**
 * Main class for the Kurokishi task management chatbot.
 * Handles the main program loop and coordinates between UI, storage, and commands.
 * 
 * The Kurokishi chatbot allows users to manage tasks through a command-line interface.
 * It supports adding, marking, deleting, and listing various types of tasks including
 * todos, deadlines, and events.
 * 
 * @author Kurokishi592
 * @version 1.0
 * @since 2025-09-30
 */
public class Kurokishi {
    /** User interface handler for displaying messages and interactions */
    private final Ui ui;

    /** Storage handler for saving and loading tasks from file */
    private final Storage storage;

    /** Task list manager containing all user tasks */
    private final TaskList tasks;

    /** Scanner for reading user input from console */
    private final Scanner scanner;

    /**
     * Constructs a new Kurokishi chatbot instance.
     * Initializes the UI, storage system, task list, and input scanner.
     * 
     * @param filePath Path to the data file for task storage
     */
    public Kurokishi(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage);
        this.scanner = new Scanner(System.in);
    }

    /**
     * Runs the interactive command loop.
     */
    public void run() {
        ui.showLogo();
        ui.showBotIntro();

        boolean isRunning = true;
        while (isRunning) {
            try {
                ui.showPrompt();
                String input = scanner.nextLine();
                ui.showDash();

                Command command = Parser.parse(input);
                boolean shouldExit = command.execute(tasks, ui);

                if (shouldExit) {
                    ui.showEnd();
                    isRunning = false;
                    break;
                } 
                if (!input.trim().equalsIgnoreCase("list")) {
                    tasks.saveTasks();
                }
            } catch (InputException | StorageException e) {
                ui.showError(e.getMessage());
            }
            ui.showDone();
        }
        scanner.close();
    }

    /**
     * Entry point of the Kurokishi application.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        Kurokishi kurokishi = new Kurokishi("data/tasks.txt");
        kurokishi.run();
    }
}