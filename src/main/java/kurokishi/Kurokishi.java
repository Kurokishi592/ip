package kurokishi;

import kurokishi.ui.Ui;
import kurokishi.task.TaskList;
import kurokishi.parser.Parser;
import kurokishi.command.Command;
import kurokishi.exception.*;
import kurokishi.data.Storage;

import java.util.Scanner;

public class Kurokishi {
    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storage = new Storage("data/tasks.txt");
        TaskList tasks = new TaskList(storage);
        Scanner in = new Scanner(System.in);

        ui.printLogo();
        ui.printBotIntro();

        while (true) {
            ui.printDash();
            ui.printPrompt();
            String input = in.nextLine();
            ui.printDash();
            try {
                Command command = Parser.parse(input);
                boolean isExit = command.execute(tasks, ui);
                if (isExit) {
                    ui.printEnd();
                    ui.printDash();
                    break;
                }
                if (!input.trim().equalsIgnoreCase("list")) {
                    tasks.saveTasks();
                }
            } catch (InputException e) {
                ui.printError(e.getMessage());
            } catch (StorageException e) {
                ui.printError(e.getMessage());
            }
            ui.printDash();
            ui.printDone();
        }
        in.close();
    }
}