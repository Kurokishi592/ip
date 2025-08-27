import java.util.Scanner;

public class Kurokishi {
    public static void main(String[] args) {
        String name = "Kurokishi";
        String dash_line ="------------------------------------------------------------";
        String prompt_for_command = "Please type something:) ";
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
        System.out.println(dash_line);
        System.out.println(prompt_for_command);

        // Echos commands by the user until the user types "bye"
        Scanner in = new Scanner(System.in);
        String command;
        do {
            command = in.nextLine();
            if (!command.equals("bye")) {
                System.out.println(dash_line);
                System.out.println("    " + command);
                System.out.println(dash_line);
                System.out.println(prompt_for_command);
            }
        } while (!command.equals("bye"));
        System.out.println(dash_line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(dash_line);
    }
}
