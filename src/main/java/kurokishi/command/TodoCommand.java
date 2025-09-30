package kurokishi.command;


import kurokishi.task.TaskList;
import kurokishi.task.Todo;
import kurokishi.exception.InputException;
import kurokishi.ui.Ui;


public class TodoCommand implements Command {
    private final String todoString;

    public TodoCommand(String todoString) { 
        this.todoString = todoString; 
    }

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