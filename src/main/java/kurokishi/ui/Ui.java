package kurokishi.ui;

/*
 * Ui class handles all printing
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

    public void showDash() {
        System.out.println(DASH_LINE);
    }

    public void showLogo() {
        System.out.println(KUROKISHI_LOGO);
    }

    public void showBotIntro() {
        System.out.println(BOT_INTRO);
    }

    public void showPrompt() {
        System.out.println(PROMPT_COMMAND);
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }

    public void showError(String msg) {
        System.out.println(msg);
    }

    public void showDone() {
        showDash();
        System.out.println(DONE_COMMAND);
    }

    public void showEnd() {
        System.out.println(END_COMMAND);
        showDash();
    }
}
