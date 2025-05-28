package bg.tu_varna.sit.a1.f23621627.core;

import bg.tu_varna.sit.a1.f23621627.commands.*;
import bg.tu_varna.sit.a1.f23621627.commands.main.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages and executes user commands.
 */
public class CommandManager {
    private final Map<CommandType, Command> commands = new HashMap<>();

    /**
     * Creates a CommandManager and initializes all supported commands.
     *
     * @param fileManager used by commands that work with files
     */
    public CommandManager(FileManager fileManager) {
        commands.put(CommandType.OPEN, new OpenCommand(fileManager));
        commands.put(CommandType.CLOSE, new CloseCommand(fileManager));
        commands.put(CommandType.SAVE, new SaveCommand(fileManager));
        commands.put(CommandType.SAVE_AS, new SaveAsCommand(fileManager));
        commands.put(CommandType.HELP, new HelpCommand());
        commands.put(CommandType.EXIT, new ExitCommand());

        commands.put(CommandType.PRINT, new PrintCommand(fileManager));
        commands.put(CommandType.VALIDATE, new ValidateCommand(fileManager));
        commands.put(CommandType.SEARCH, new SearchCommand(fileManager));
        commands.put(CommandType.SET, new SetCommand(fileManager));
        commands.put(CommandType.CREATE, new CreateCommand(fileManager));
        commands.put(CommandType.DELETE, new DeleteCommand(fileManager));
        commands.put(CommandType.MOVE, new MoveCommand(fileManager));
    }

    /**
     * Executes a command based on the input string.
     *
     * @param input the full user input (e.g. "open file.json")
     */
    public void executeCommand(String input) {
        if (input == null || input.isBlank()) {
            System.out.println("Empty command.");
            return;
        }

        String[] parts = input.trim().split("\\s+", 2);
        String commandStr = parts[0].toLowerCase();
        String args = parts.length > 1 ? parts[1].replace("\"", "") : null;

        CommandType commandType = CommandType.fromString(commandStr);
        Command command = commands.get(commandType);

        if (command != null) {
            command.execute(args);
        } else {
            System.out.println("Invalid command. Type 'help' to see available commands.");
        }
    }
}
