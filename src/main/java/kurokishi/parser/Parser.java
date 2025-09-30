package kurokishi.parser;

import kurokishi.command.*;
import kurokishi.exception.InputException;

/**
 * Handles all user command line inputs for the Kurokishi chatbot.
 */
public class Parser {
    public static Command parse(String input) throws InputException {
        if (input == null) {
            throw new InputException("    [ERROR] Empty input! Please provide an input.");
        }
        if (input.trim().isEmpty()) {
            throw new InputException("    ERROR] Empty command. Please enter a command.");
        }
        String[] parts = input.trim().split(" ", 2);
        String commandWord = parts[0].toLowerCase();
        String args = parts.length > 1 ? parts[1] : "";

        switch (commandWord) {
            case "add":
                return new AddCommand(args);
            case "delete":
                return new DeleteCommand(args);
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(args, true);
            case "unmark":
                return new MarkCommand(args, false);
            case "todo":
                return new TodoCommand(args);
            case "deadline":
                return new DeadlineCommand(args);
            case "event":
                return new EventCommand(args);
            case "find":
                return new FindCommand(args);
            case "bye":
                return new ExitCommand();
            default: 
                throw new InputException("    [ERROR] Unrecognized command: '" + input + "'.\n" +   
                        "    [SYSTEM NOTICE] Please use a valid command word:" +
                        "    (known commands by Humanity: add, list, mark, unmark, todo, deadline, event, bye).\n" +
                        "    [SYSTEM NOTICE] User to follow instructions more carefully in future.");
        }
    }
}
