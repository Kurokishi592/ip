import java.util.Scanner;

/**
 * Kurokishi is a simple command-line chatbot that echoes user commands until the user types "bye".
 * It displays a logo and prompts the user for input.
 */

public class Kurokishi {
    // this array stores the commands input by user
    private static String[] commandList = new String[100];

    public static void main(String[] args) {
        String name = "Kurokishi";
        String dashLine ="------------------------------------------------------------";
        String promptCommand = "Please type something:) ";
        String logo =
                        " _  __  \n"
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
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        System.out.println(dashLine);
        System.out.println(promptCommand);

        // Echos commands by the user until the user types "bye".
        Scanner in = new Scanner(System.in);
        String command;
        int commandCount = 0;
        do {
            command = in.nextLine();
            if (!command.equals("bye")) {
                System.out.println(dashLine);
                if (command.equals("list")) {
                    for (int i = 0; i < commandCount; i++) {
                        System.out.println((i + 1) + ". " + commandList[i]);
                    }
                }
                else {
                    commandList[commandCount] = command;
                    System.out.println("    added: " + command);
                    commandCount++;
                }
                System.out.println(dashLine);
                System.out.println(promptCommand);
            }
        } while (!command.equals("bye"));
        System.out.println(dashLine);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(dashLine);
    }
}
