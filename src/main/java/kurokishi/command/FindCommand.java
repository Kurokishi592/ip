package kurokishi.command;

import kurokishi.exception.InputException;
import kurokishi.task.Task;
import kurokishi.task.TaskList;
import kurokishi.ui.Ui;

import java.util.List;

/*
 * Command to find tasks whose descriptions contain the given keyword (case-insensitive).
 */
public class FindCommand implements Command {
    private final String keyword;

    /**
     * Creates a FindCommand.
     *
     * @param keywordString Keyword to search for.
     */
    public FindCommand(String keywordString) {
        this.keyword = keywordString == null ? "" : keywordString.trim();
    }

    /**
     * Executes the search and prints matches.
     *
     * @param tasks Task list to search.
     * @param ui UI handler for output.
     * @return False to continue running.
     * @throws InputException If keyword is missing.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui) throws InputException {
        if (keyword.isEmpty()) {
            throw new InputException("    [ERROR] Please specify a search keyword.\n" +
                    "    [SYSTEM NOTICE] Usage: find <keyword>");
        }

        List<Task> matches = tasks.find(keyword);
        if (matches.isEmpty()) {
            ui.showMessage("    [SYSTEM NOTICE] No matching tasks found for: " + keyword);
        } else {
            ui.showMessage("    Here are the matching tasks in your list:");
            for (int i = 0; i < matches.size(); i++) {
                ui.showMessage("     " + (i + 1) + "." + matches.get(i));
            }
        }
        return false;
    }
}
