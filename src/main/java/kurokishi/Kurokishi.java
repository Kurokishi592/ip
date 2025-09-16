package kurokishi.ui;
import kurokishi.task.Deadline;
import kurokishi.task.Event;
import kurokishi.task.Task;
import kurokishi.task.Todo;
import kurokishi.exception.KurokishiException;

import java.util.Scanner;

public class Kurokishi {
    
    private static final int MAX_TASKS = 100;
    private static final String DASH_LINE ="------------------------------------------------------------";
    private static final String NAME = "Kurokishi";
    private static final String PROMPT_COMMAND = (
            ">> SYSTEM DIRECTIVE: Awaiting your command...\n" + 
            ">>     Use 'add <description>' to register a new item.\n" +
            ">>     Use 'list' to review all stored records.\n" +
            ">>     Use 'mark <task number>' to confirm task completion.\n" +
            ">>     Use 'unmark <task number>' to revoke completion status.\n" +
            ">>     Use 'todo <description>' to log a standard task.\n" +
            ">>     Use 'deadline <description> /by <date/time>' to establish a time-bound objective.\n" +
            ">>     Use 'event <description> /from <start> /to <end>' to schedule an occurrence.\n" +
            ">>     Use 'bye' to terminate the current connection.\n" +
            ">> SYSTEM DIRECTIVE: Standing by for your command.\n"
    );

    // A simple array to store tasks
    private static Task[] taskList = new Task[MAX_TASKS];
    
    public static void main(String[] args) {
        printLogo();
        printBotIntro();
        printPrompt();
        printDashLine();
        
        // Reads command by user and execute corresponding action
        Scanner in = new Scanner(System.in);
        int taskIndex = 0;
        
        while(true) {
            String input = in.nextLine().trim();
            String[] parts = input.split(" ", 2);
            String command = parts[0];
            printDashLine();

            // Exit the program upon "bye" command
            try {
                if (command.equals("bye")) {
                    System.out.println("[SYSTEM NOTICE] Session concluded. Probability of future contact: high. Glory to Humanity.");
                    printDashLine();
                    break;
                }
                
                switch (command) {
                    case "add":
                        if (parts.length < 2 || parts[1].trim().isEmpty()) {
                            throw new KurokishiException("    [ERROR] Missing item description for 'add' command.\n" +
                                    "    [SYSTEM NOTICE] Please provide a valid description to add an item.");
                        }
                        taskIndex = handleAdd(parts[1], taskIndex);
                        break;
                    case "list":
                        printTaskList(taskIndex);
                        break;
                    // handle both 'mark' and 'unmark' user inputs together
                    case "mark":
                    case "unmark":
                        handleMarkUnmark(parts, command, taskIndex);
                        break;
                    case "todo":
                        if (parts.length < 2 || parts[1].trim().isEmpty()) {
                            throw new KurokishiException("    [ERROR] Missing todo task description.\n" +
                                    "    [SYSTEM NOTICE] Please provide a valid description to add a todo task.");
                        }
                        taskIndex = handleTodo(parts, taskIndex);
                        break;
                    case "deadline":
                        if (parts.length < 2 || parts[1].trim().isEmpty()) {
                            throw new KurokishiException("    [ERROR] Missing deadline task description and time.\n" +
                                    "    [SYSTEM NOTICE] Please provide a valid description and time using '/by' to add a deadline task.");
                        }
                        taskIndex = handleDeadline(parts, taskIndex);
                        break;
                    case "event":
                        if (parts.length < 2 || parts[1].trim().isEmpty()) {
                            throw new KurokishiException("    [ERROR] Missing event task description, start time and end time.\n" +
                                    "    [SYSTEM NOTICE] Please provide a valid description, start time using '/from' and end time using '/to' to add an event task.");
                        }
                        taskIndex = handleEvent(parts, taskIndex);
                        break;
                    default:
                        throw new KurokishiException("    [ERROR] Unrecognized command: '" + input + "'.\n" +   
                                "    [SYSTEM NOTICE] Please use a valid command word:" +
                                "    (known commands by Humanity: add, list, mark, unmark, todo, deadline, event, bye).\n" +
                                "    [SYSTEM NOTICE] User to follow instructions more carefully in future.");
                }
            } catch (KurokishiException e) {
                System.out.println(e.getMessage());
            }
            // Indicate user command executed and prompt for next command
            printDashLine();
            printDone();
            printPrompt();
        }
    }

    private static void printLogo() {
        String logo = " _  __  \n"
                + "| |/ /  \n"
                + "| ' /   \n"
                + "| . \\   \n"
                + "|_|\\_\\  \n"
                + " _    _  \n"
                + "| |  | | \n"
                + "| |  | | \n"
                + "| |__| | \n"
                + " \\____/  \n"
                + " ____   \n"
                + "|  _ \\  \n"
                + "| |_) | \n"
                + "|  _ <  \n"
                + "|_| \\_\\ \n"
                + "  ____   \n"
                + " / __ \\  \n"
                + "| |  | | \n"
                + "| |__| | \n"
                + " \\____/  \n";
        System.out.println(logo);
    }

    private static void printBotIntro() {
        System.out.println("Unit " + NAME + " activated.\n" +
                "My role is to support you in organizing your tasks.\n");
    }
    private static void printDashLine() {
        System.out.println(DASH_LINE);
    }

    private static void printPrompt() {
        System.out.println(PROMPT_COMMAND);
    }

    private static void printDone() {
        System.out.println("[SYSTEM NOTICE] Ready for next command. Glory to Humanity!\n");
    }
    
    private static void printTaskList(int taskIndex) {
        System.out.println("[SYSTEM NOTICE] Compiling list of active tasks:");
        for (int i = 0; i < taskIndex; i++) {
            System.out.println((i + 1) + ". " + taskList[i]);
        }
    }

    private static void handleMarkUnmark(String[] parts, String command, int taskIndex) throws KurokishiException {
        // Handle unexpected input
        if (parts.length < 2) {
            throw new KurokishiException("    [ERROR] Please specify a task number.\n" +
                    "    [SYSTEM NOTICE] Usage: " + command + " <task number>");
        }
        try {
            int taskNumber = Integer.parseInt(parts[1]) - 1; // indexed zero 
            if (taskNumber < 0 || taskNumber >= taskIndex) {
                throw new KurokishiException("    [ERROR] Input does not match valid task index.\n" +
                        "    [SYSTEM NOTICE] Use 'list' to view valid task numbers.");
            }
            boolean isMark = command.equals("mark");
            taskList[taskNumber].setDone(isMark);
            if (isMark) {
                System.out.println("    [SYSTEM UPDATE] Task status: marked as complete.");
            } else {
                System.out.println("    [SYSTEM UPDATE] Task status: reverted to incomplete.");
            }
            System.out.println("    " + taskList[taskNumber]);
        } catch (NumberFormatException e) {
            throw new KurokishiException("    [ERROR] Invalid task number format\n" +
                    "    [SYSTEM NOTICE] Task number must be an integer.");
        }
    }

    private static int handleTodo(String[] parts, int taskIndex) throws KurokishiException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new KurokishiException("    [ERROR] Missing todo task description.\n" +
                    "    [SYSTEM NOTICE] Usage: todo <description>");
        }
        if (taskIndex >= MAX_TASKS) {
            throw new KurokishiException("    [SYSTEM WARNING] Memory capacity exceeded. Task list full.");
        }
        Todo todoTask = new Todo(parts[1].trim()); // get rid of the space used to split too
        taskList[taskIndex] = todoTask;
        System.out.println("    [SYSTEM NOTICE] Todo task added successfully.\n " + "         " + todoTask);
        taskIndex++;
        System.out.println("    [STATUS] Current number of active tasks: " + taskIndex);
        return taskIndex;
    }

    private static int handleDeadline(String[] parts, int taskIndex) throws KurokishiException {
        if (parts.length < 2 || !parts[1].contains(" /by ")) {
            throw new KurokishiException("    [ERROR] Please specify a deadline description and time using '/by'.\n" +
                    "    [SYSTEM NOTICE] Usage: deadline <description> /by <date/time>");
        }
        if (taskIndex >= MAX_TASKS) {
            throw new KurokishiException("    [SYSTEM WARNING] Memory capacity exceeded. Task list full.");
        }
        String[] deadlineParts = parts[1].split(" /by ", 2);
        Deadline deadlineTask = new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim());
        taskList[taskIndex] = deadlineTask;
        System.out.println("    [SYSTEM NOTICE] Deadline task added successfully.\n " + "         " + deadlineTask);
        taskIndex++;
        System.out.println("    [STATUS] Current number of active tasks: " + taskIndex);
        return taskIndex;
    }

    private static int handleEvent(String[] parts, int taskIndex) throws KurokishiException {
        if (parts.length < 2 || !parts[1].contains(" /from ") || !parts[1].contains(" /to ")) {
        throw new KurokishiException("    [ERROR] Please specify an event description, start time using '/from' and end time using '/to'.\n" +
                "    [SYSTEM NOTICE] Usage: event <description> /from <start> /to <end>");
        }
        if (taskIndex >= MAX_TASKS) {
            throw new KurokishiException("    [SYSTEM WARNING] Memory capacity exceeded. Task list full.");
        }
        String[] eventParts1 = parts[1].split(" /from ", 2);
        String[] eventParts2 = eventParts1[1].split(" /to ", 2);
        Event eventTask = new Event(eventParts1[0].trim(), eventParts2[0].trim(), eventParts2[1].trim());
        taskList[taskIndex] = eventTask;
        System.out.println("    [SYSTEM NOTICE] Event task added successfully.\n " + "         " + eventTask);
        taskIndex++;
        System.out.println("    [STATUS] Current number of active tasks: " + taskIndex);
        return taskIndex;
    }

    private static int handleAdd(String input, int taskIndex) throws KurokishiException {
        if (input.trim().isEmpty()) {
            throw new KurokishiException("    [ERROR] Missing item description for 'add' command.\n" +
                    "    [SYSTEM NOTICE] Usage: add <description>");
        }
        if (taskIndex >= MAX_TASKS) {
            throw new KurokishiException("    [SYSTEM WARNING] Memory capacity exceeded. Task list full.");
        }
        Task t = new Task(input);
        taskList[taskIndex] = t;
        System.out.println("    [SYSTEM NOTICE] Item has been registered in memory: " + t);
        taskIndex++;
        return taskIndex;
    }
}