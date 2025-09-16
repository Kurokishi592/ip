package kurokishi.command;

import kurokishi.task.TaskList;
import kurokishi.ui.Ui;

public class ListCommand implements Command {
    @Override
    public boolean execute(TaskList tasks, Ui ui) {
        ui.printMessage("[SYSTEM NOTICE] Compiling list of active tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            try {
                ui.printMessage((i + 1) + ". " + tasks.get(i));
            } catch (Exception e) {
                // should not happen because index is in-bounds
            }
        }
        return false;
    }
}