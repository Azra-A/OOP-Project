package bg.tu_varna.sit.a1.f23621627.commands;

import bg.tu_varna.sit.a1.f23621627.core.FileManager;
import bg.tu_varna.sit.a1.f23621627.json.JsonMap;
import bg.tu_varna.sit.a1.f23621627.json.JsonObject;

/**
 * Command that moves a JSON element from one path to another.
 */
public class MoveCommand implements Command {
    private final FileManager fileManager;

    /**
     * Constructs a MoveCommand with the provided FileManager instance.
     *
     * @param fileManager the file manager managing the currently loaded JSON file
     */
    public MoveCommand(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    /**
     * Executes the move command.
     * Expected usage: move fromPath toPath
     *
     * @param args a string containing the source and destination paths, separated by whitespace
     */
    @Override
    public void execute(String args) {
        if (!fileManager.isFileLoaded()) {
            System.out.println("No file is currently open.");
            return;
        }

        if (args == null || args.isBlank()) {
            System.out.println("Usage: move <fromPath> <toPath>");
            return;
        }

        String[] parts = args.trim().split("\\s+", 2);
        if (parts.length != 2) {
            System.out.println("Invalid move arguments. Use: move <fromPath> <toPath>");
            return;
        }

        String fromPath = parts[0];
        String toPath = parts[1];

        JsonObject root = fileManager.getRoot();
        if (!(root instanceof JsonMap)) {
            System.out.println("Root is not a valid JSON object.");
            return;
        }

        JsonMap jsonMap = (JsonMap) root;

        JsonObject valueToMove = jsonMap.getByPath(fromPath);
        if (valueToMove == null) {
            System.out.println("No element found at source path: " + fromPath);
            return;
        }

        if (jsonMap.getByPath(toPath) != null) {
            System.out.println("Destination path already exists: " + toPath);
            return;
        }

        jsonMap.createByPath(toPath, valueToMove);
        jsonMap.deleteByPath(fromPath);

        System.out.println("Successfully moved element from '" + fromPath + "' to '" + toPath + "'.");
    }
}
