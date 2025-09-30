package kurokishi.command;

import kurokishi.task.TaskList;
import kurokishi.ui.Ui;

public class ListCommand implements Command {
    @Override
    public boolean execute(TaskList tasks, Ui ui) {
        ui.showMessage("[SYSTEM NOTICE] Compiling list of active tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            try {
                ui.showMessage((i + 1) + ". " + tasks.get(i));
            } catch (Exception e) {
                // should not happen because index is in-bounds
            }
        }
        return false;
    }
}