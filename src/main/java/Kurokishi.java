import java.util.Scanner;

public class Kurokishi {
    
    private static final int MAX_TASKS = 100;
    private static final String DASH_LINE ="------------------------------------------------------------";
    private static final String NAME = "Kurokishi";
    private static final String PROMPT_COMMAND = (
            "Simply type your task to add to a list\n" +
            "Type 'list' to see your tasks\n" +
            "Type 'mark <task number>' to mark a task as done\n" +
            "Type 'unmark <task number>' to unmark a task as not done\n" +
            "Type 'todo <description>' to add a todo task\n" +
            "Type 'deadline <description> /by <date/time>' to add a deadline task\n" +
            "Type 'event <description> /from <day/time> /to <day/time>' to add an event task\n" +
            "Type 'bye' to exit the program\n"
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
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                printDashLine();
                break;
            }
            
            switch (command) {
                case "list":
                    printTaskList(taskIndex);
                    break;
                // handle both 'mark' and 'unmark' user inputs together
                case "mark":
                case "unmark":
                    handleMarkUnmark(parts, command, taskIndex);
                    break;
                case "todo":
                    taskIndex = handleTodo(parts, taskIndex);
                    break;
                case "deadline":
                    taskIndex = handleDeadline(parts, taskIndex);
                    break;
                case "event":
                    taskIndex = handleEvent(parts, taskIndex);
                    break;
                default:
                    taskIndex = handleDefault(input, taskIndex);
                    break;
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
        System.out.println("Hello! I'm " + NAME +"\n" + "I can help you keep track of your tasks!\n");
    }
    private static void printDashLine() {
        System.out.println(DASH_LINE);
    }

    private static void printPrompt() {
        System.out.println(PROMPT_COMMAND);
    }

    private static void printDone() {
        System.out.println("Done! Now,\n");
    }
    
    private static void printTaskList(int taskIndex) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskIndex; i++) {
            System.out.println((i + 1) + ". " + taskList[i]);
        }
    }

    private static void handleMarkUnmark(String[] parts, String command, int taskIndex) {
        // Handle unexpected input
        if (parts.length < 2) {
            System.out.println("    Please specify a task number.");
            return;
        }
        try {
            int taskNumber = Integer.parseInt(parts[1]) - 1; // indexed zero 
            if (taskNumber >= 0 && taskNumber < taskIndex) {
                boolean isMark = command.equals("mark");
                taskList[taskNumber].setDone(isMark);
                if (isMark) {
                    System.out.println("    Nice! I've marked this task as done:");
                } else {
                    System.out.println("    OK, I've marked this task as not done yet:");
                }
                System.out.println("    " + taskList[taskNumber]);
            } else {
                System.out.println("    Invalid task number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("    Invalid task number format");
        }
    }

    private static int handleTodo(String[] parts, int taskIndex) {
        if (parts.length < 2) {
            System.out.println("    Please specify a todo description.");
            return taskIndex;
        }
        if (taskIndex >= MAX_TASKS) {
            System.out.println("    Task list is full.");
            return taskIndex;
        }
        Todo todoTask = new Todo(parts[1].trim()); // get rid of the space used to split too
        taskList[taskIndex] = todoTask;
        System.out.println("    Got it. I've added this todo task:\n " + "         " + todoTask);
        taskIndex++;
        System.out.println("    Now you have " + taskIndex + " tasks in the list.");
        return taskIndex;
    }

    private static int handleDeadline(String[] parts, int taskIndex) {
        if (parts.length < 2 || !parts[1].contains(" /by ")) {
            System.out.println("    Please specify a deadline description and time using '/by'.");
            return taskIndex;
        }
        if (taskIndex >= MAX_TASKS) {
            System.out.println("    Task list is full.");
            return taskIndex;
        }
        String[] deadlineParts = parts[1].split(" /by ", 2);
        Deadline deadlineTask = new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim());
        taskList[taskIndex] = deadlineTask;
        System.out.println("    Got it. I've added this deadline task:\n " + "         " + deadlineTask);
        taskIndex++;
        System.out.println("    Now you have " + taskIndex + " tasks in the list.");
        return taskIndex;
    }

    private static int handleEvent(String[] parts, int taskIndex) {
        if (parts.length < 2 || !parts[1].contains(" /from ") || !parts[1].contains(" /to ")) {
            System.out.println("    Please specify an event description, start time using '/from' and end time using '/to'.");
            return taskIndex;
        }
        if (taskIndex >= MAX_TASKS) {
            System.out.println("    Task list is full.");
            return taskIndex;
        }
        String[] eventParts1 = parts[1].split(" /from ", 2);
        String[] eventParts2 = eventParts1[1].split(" /to ", 2);
        Event eventTask = new Event(eventParts1[0].trim(), eventParts2[0].trim(), eventParts2[1].trim());
        taskList[taskIndex] = eventTask;
        System.out.println("    Got it. I've added this event task:\n " + "         " + eventTask);
        taskIndex++;
        System.out.println("    Now you have " + taskIndex + " tasks in the list.");
        return taskIndex;
    }

    private static int handleDefault(String input, int taskIndex) {
        if (taskIndex >= MAX_TASKS) {
            System.out.println("    Task list is full.");
            return taskIndex;
        }
        Task t = new Task(input);
        taskList[taskIndex] = t;
        System.out.println("    added: " + t);
        taskIndex++;
        return taskIndex;
    }
}