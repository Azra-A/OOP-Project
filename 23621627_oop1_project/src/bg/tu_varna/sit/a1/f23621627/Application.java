package bg.tu_varna.sit.a1.f23621627;

import bg.tu_varna.sit.a1.f23621627.core.*;

import java.util.Scanner;

/**
 * Entry point of the program that reads user input
 * and executes commands in a loop.
 */
public class Application {

    /**
     * Starts the application and runs the command loop.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileManager fileManager = new FileManager();
        CommandManager commandManager = new CommandManager(fileManager);

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            commandManager.executeCommand(input);
        }
    }
}
