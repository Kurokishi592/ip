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
        String trimmed = input.trim();
        if (trimmed.isEmpty()) {
            throw new InputException("    ERROR] Empty command. Please enter a command.");
        }
        String[] parts = input.trim().split(" ", 2);
        String commandWord = parts[0];

        switch (commandWord) {
            case "add":
                return new AddCommand(parts.length > 1 ? parts[1] : "");
            case "delete":
                return new DeleteCommand(parts.length > 1 ? parts[1] : "");
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(parts.length > 1 ? parts[1] : "", true);
            case "unmark":
                return new MarkCommand(parts.length > 1 ? parts[1] : "", false);
            case "todo":
                return new TodoCommand(parts.length > 1 ? parts[1] : "");
            case "deadline":
                return new DeadlineCommand(parts.length > 1 ? parts[1] : "");
            case "event":
                return new EventCommand(parts.length > 1 ? parts[1] : "");
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
