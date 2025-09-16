package kurokishi;

import kurokishi.ui.Ui;
import kurokishi.task.TaskList;
import kurokishi.parser.Parser;
import kurokishi.command.Command;
import kurokishi.exception.InputException;

import java.util.Scanner;

public class Kurokishi {
    public static void main(String[] args) {
        Ui ui = new Ui();
        TaskList tasks = new TaskList();
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
            } catch (InputException e) {
                ui.printError(e.getMessage());
            }
            ui.printDash();
            ui.printDone();
        }
    }
}