package kurokishi.ui;

/*
 * Handles user interface output.
 */
public class Ui {
    private static final String DASH_LINE ="------------------------------------------------------------";
    private static final String NAME = "Kurokishi";
    private static final String BOT_INTRO = ("Unit " + NAME + " activated.\n" +
            "My role is to support you in organizing your tasks.\n"
    );
    private static final String PROMPT_COMMAND = (">> SYSTEM DIRECTIVE: Awaiting your command...\n" + 
            ">>     Use 'add <description>' to register a new item.\n" +
            ">>     Use 'delete <task number>' to trash a memory.\n" +
            ">>     Use 'list' to review all stored records.\n" +
            ">>     Use 'mark <task number>' to confirm task completion.\n" +
            ">>     Use 'unmark <task number>' to revoke completion status.\n" +
            ">>     Use 'todo <description>' to log a standard task.\n" +
            ">>     Use 'deadline <description> /by <yyyy-MM-dd HHmm>' to establish a time-bound objective.\n" +
            ">>     Use 'event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>' to schedule an occurrence.\n" +
            ">>     Use 'find <keyword>' to search your records.\n" +
            ">>     Use 'bye' to terminate the current connection.\n" +
            ">> SYSTEM DIRECTIVE: Standing by for your command.\n"
    );
    private static final String DONE_COMMAND = ("[SYSTEM NOTICE] Ready for next command. Glory to Humanity!\n");
    private static final String END_COMMAND = ("[SYSTEM NOTICE] Session concluded. Probability of future contact: high. Glory to Humanity.");
    private static final String KUROKISHI_LOGO = (" _  __  \n"
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
            + " \\____/  \n"
    );

    /**
     * Displays a horizontal dash line.
     */
    public void showDash() {
        System.out.println(DASH_LINE);
    }

    /**
     * Displays the Kurokishi logo.
     */
    public void showLogo() {
        System.out.println(KUROKISHI_LOGO);
    }

    /**
     * Displays the bot introduction message.
     */
    public void showBotIntro() {
        System.out.println(BOT_INTRO);
    }

    /**
     * Displays the command prompt message.
     */
    public void showPrompt() {
        System.out.println(PROMPT_COMMAND);
    }

    /**
     * Displays a general message to the user.
     *
     * @param msg Message to display.
     */
    public void showMessage(String msg) {
        System.out.println(msg);
    }

    /**
     * Displays an error message to the user.
     *
     * @param msg Error message to display.
     */
    public void showError(String msg) {
        System.out.println(msg);
    }

    /**
     * Displays the done command message.
     */
    public void showDone() {
        showDash();
        System.out.println(DONE_COMMAND);
    }

    /**
     * Displays the end command message.
     */
    public void showEnd() {
        System.out.println(END_COMMAND);
        showDash();
    }
}
