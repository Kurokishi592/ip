import java.util.Scanner;

public class Kurokishi {
    
    // A simple array to store tasks
    private static String[] taskList = new String[100];
    
    public static void main(String[] args) {
        String name = "Kurokishi";
        String dashLine ="------------------------------------------------------------";
        String promptCommand = ("Simply type your task to add to a list\n" +
        "Type 'list' to see your tasks\n" +
        "Type 'mark <task number>' to mark a task as done\n" +
        "Type 'unmark <task number>' to unmark a task as not done\n" +
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
                        System.out.println((i + 1) + ". " + taskList[i]);
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
                        String taskToMark = taskList[taskNumber];
                        Task t = new Task(taskToMark.substring(4)); // Skip status icon and space
                        if (input.startsWith("mark ")) {
                            t.setDone(true);
                            System.out.println("    Nice! I've marked this task as done:");
                        } else {
                            t.setDone(false);
                            System.out.println("    OK, I've marked this task as not done yet:");
                        }
                        taskList[taskNumber] = t.getStatusIcon() + " " + t.description;
                        System.out.println("    " + taskList[taskNumber]);
                    } else {
                        System.out.println("    Invalid task number.");
                    }
                    break;
                
                default:
                    Task t = new Task(input);
                    String task = t.getStatusIcon() + " " + t.description;
                    taskList[taskIndex] = task;
                    System.out.println("    added: " + task);
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
