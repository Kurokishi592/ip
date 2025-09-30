package kurokishi.command;

import kurokishi.task.TaskList;
import kurokishi.task.Todo;
import kurokishi.exception.InputException;
import kurokishi.ui.Ui;

/**
 * Command to add a todo task.
 */
public class TodoCommand implements Command {
    private final String todoString;

    /**
     * Creates a TodoCommand.
     *
     * @param todoString Todo task description.
     */
    public TodoCommand(String todoString) {
        this.todoString = todoString;
    }

    /**
     * Adds a todo task with the provided description.
     *
     * @param tasks Task list to add to.
     * @param ui UI handler for output.
     * @return False to continue running.
     * @throws InputException If description is missing.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui) throws InputException {
        if (todoString == null || todoString.trim().isEmpty()) {
            throw new InputException("    [ERROR] Missing todo task description.\n" +
                    "    [SYSTEM NOTICE] Usage: todo <description>");
        }
        Todo todo = new Todo(todoString.trim());
        tasks.add(todo);
        ui.showMessage("    [SYSTEM NOTICE] Todo task added successfully.\n " + "         " + todo);
        ui.showMessage("    [STATUS] Current number of active tasks: " + tasks.size());
        return false;
    }
}