package bg.tu_varna.sit.a1.f23621627.commands.main;

import bg.tu_varna.sit.a1.f23621627.commands.Command;

/**
 * Command that exits the application.
 */
public class ExitCommand implements Command {

    /**
     * Executes the exit command, printing a message and terminating the program.
     *
     * @param args unused argument
     */
    @Override
    public void execute(String args) {
        System.out.println("Exiting the program...");
        System.exit(0);
    }
}
