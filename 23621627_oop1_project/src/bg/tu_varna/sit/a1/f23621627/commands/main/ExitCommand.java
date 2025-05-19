package bg.tu_varna.sit.a1.f23621627.commands.main;

import bg.tu_varna.sit.a1.f23621627.commands.Command;

/**
 * Command that exits the program.
 */
public class ExitCommand implements Command {

    /**
     * Executes the exit command.
     *
     * @param arguments not used
     */
    @Override
    public void execute(String arguments) {
        System.out.println("Exiting the program...");
        System.exit(0);
    }
}
