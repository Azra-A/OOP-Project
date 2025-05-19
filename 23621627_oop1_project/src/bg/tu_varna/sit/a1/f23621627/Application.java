package bg.tu_varna.sit.a1.f23621627;

import bg.tu_varna.sit.a1.f23621627.core.*;

import java.util.Scanner;

public class Application {
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
