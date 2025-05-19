package bg.tu_varna.sit.a1.f23621627.commands.main;

import bg.tu_varna.sit.a1.f23621627.commands.Command;

public class ExitCommand implements Command {
    @Override
    public void execute(String arguments) {
        System.out.println("Exiting the program...");
        System.exit(0);
    }
}

