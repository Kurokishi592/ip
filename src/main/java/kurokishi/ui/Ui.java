package kurokishi.ui;

public class Ui {
    private static final String DASH_LINE ="------------------------------------------------------------";
    private static final String NAME = "Kurokishi";

    public void printLogo() {
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

    public void printBotIntro() {
        System.out.println("Unit " + NAME + " activated.\n" +
                "My role is to support you in organizing your tasks.\n");
    }

    public void printPrompt() {
        String PROMPT_COMMAND = (">> SYSTEM DIRECTIVE: Awaiting your command...\n" + 
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
        System.out.println(PROMPT_COMMAND);
    }

    public void printDash() {
        System.out.println(DASH_LINE);
    }

    public void printMessage(String msg) {
        System.out.println(msg);
    }

    public void printError(String msg) {
        System.out.println("[ERROR] " + msg);
    }

    public void printDone() {
        System.out.println("[SYSTEM NOTICE] Ready for next command. Glory to Humanity!\n");
    }
}
