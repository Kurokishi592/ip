package kurokishi.parser;

import kurokishi.command.*;
import kurokishi.exception.InputException;

/*
 * Parser.java parses commands. i.e. turns input into commands
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
            case "add": return new AddCommand(parts);
            case "list": return new ListCommand();
            case "mark": return new MarkCommand(parts);
            case "unmark": return new UnmarkCommand(parts);
            case "todo": return new TodoCommand(parts);
            case "deadline": return new DeadlineCommand(parts);
            case "event": return new EventCommand(parts);
            case "bye": return new ExitCommand();
            default: throw new InputException("Unrecognized command: " + input);
        }
    }
}
