import java.util.Scanner;

public class Kurokishi {
    
    // A simple array to store tasks
    private static Task[] taskList = new Task[100];
    
    public static void main(String[] args) {
        String name = "Kurokishi";
        String dashLine ="------------------------------------------------------------";
        String promptCommand = ("Simply type your task to add to a list\n" +
        "Type 'list' to see your tasks\n" +
        "Type 'mark <task number>' to mark a task as done\n" +
        "Type 'unmark <task number>' to unmark a task as not done\n" +
        "Type 'todo <description>' to add a todo task\n" +
        "Type 'deadline <description> /by <date/time>' to add a deadline task\n" +
        "Type 'event <description> /from <day/time> /to <day/time>' to add an event task\n" +
        "Type 'bye' to exit the program\n"
        );
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
        System.out.println("Hello! I'm " + name +"\n" +
        "I can help you keep track of your tasks!\n");
        System.out.println(promptCommand);
        System.out.println(dashLine);
        
        // Reads command by user and execute corresponding action
        Scanner in = new Scanner(System.in);
        int taskIndex = 0;
        String input;
        
        while(true) {
            input = in.nextLine();
            String[] parts = input.split(" ", 2);
            String command = parts[0];
            
            // Exit the program upon "bye" command
            if (command.equals("bye")) {
                System.out.println(dashLine);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(dashLine);
                break;
            }
            
            System.out.println(dashLine);
            
            switch (command) {
                
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskIndex; i++) {
                        System.out.println((i + 1) + ". " + taskList[i]); // Display by polymorphism
                    }
                    break;
                
                case "mark":
                case "unmark":
                    // Handle unexpected input
                    if (parts.length < 2) {
                        System.out.println("    Please specify a task number.");
                        break;
                    }
                    int taskNumber = Integer.parseInt(parts[1]) - 1; // index zero stuff
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
                    break;

                case "todo":
                    if (parts.length < 2) {
                        System.out.println("    Please specify a todo description.");
                        break;
                    }
                    Todo todoTask = new Todo(parts[1].trim()); // get rid of the space used to split too
                    taskList[taskIndex] = todoTask;
                    System.out.println("    Got it. I've added this todo task:\n " + todoTask);
                    taskIndex++;
                    System.out.println("    Now you have " + taskIndex + " tasks in the list.");
                    break;

                case "deadline":
                    if (parts.length < 2 || !parts[1].contains(" /by ")) {
                        System.out.println("    Please specify a deadline description and time using '/by'.");
                        break;
                    }
                    String[] deadlineParts = parts[1].split(" /by ", 2);
                    Deadline deadlineTask = new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim()); // also removes the spaces
                    taskList[taskIndex] = deadlineTask;
                    System.out.println("    Got it. I've added this deadline task:\n " + deadlineTask);
                    taskIndex++;
                    System.out.println("    Now you have " + taskIndex + " tasks in the list.");
                    break;
                
                case "event":
                    if (parts.length < 2 || !parts[1].contains(" /from ") || !parts[1].contains(" /to ")) {
                        System.out.println("    Please specify an event description, start time using '/from' and end time using '/to'.");
                        break;
                    }
                    String[] eventParts1 = parts[1].split(" /from ", 2);
                    String[] eventParts2 = eventParts1[1].split(" /to ", 2);
                    Event eventTask = new Event(eventParts1[0].trim(), eventParts2[0].trim(), eventParts2[1].trim()); // also removes the spaces
                    taskList[taskIndex] = eventTask;
                    System.out.println("    Got it. I've added this event task:\n " + eventTask);
                    taskIndex++;
                    System.out.println("    Now you have " + taskIndex + " tasks in the list.");
                    break;
                
                default:
                    Task t = new Task(input);
                    taskList[taskIndex] = t;
                    System.out.println("    added: " + t);
                    taskIndex++;
                    break;
            }

            // Prompt for next command
            System.out.println(dashLine);
            System.out.println("Done! Now,\n");
            System.out.println(promptCommand);
        }
    }
    
}
