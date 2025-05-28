package bg.tu_varna.sit.a1.f23621627.commands;

import bg.tu_varna.sit.a1.f23621627.core.FileManager;
import bg.tu_varna.sit.a1.f23621627.json.JsonMap;
import bg.tu_varna.sit.a1.f23621627.json.JsonObject;
import bg.tu_varna.sit.a1.f23621627.json.JsonPrimitive;

/**
 * Command to set a new value for an existing JSON element at the specified path.
 */
public class SetCommand implements Command {
    private final FileManager fileManager;

    /**
     * Constructs a SetCommand with the given FileManager.
     *
     * @param fileManager the file manager that handles the current JSON file
     */
    public SetCommand(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    /**
     * Executes the command to update the value of a JSON element.
     * The argument should contain the path and the new value separated by space.
     *
     * @param args arguments in the format "path newValue"
     */
    @Override
    public void execute(String args) {
        if (!fileManager.isFileLoaded()) {
            System.out.println("No file is currently open.");
            return;
        }

        if (args == null || args.isBlank()) {
            System.out.println("Usage: set <path> <newValue>");
            return;
        }

        String[] parts = args.trim().split("\\s+", 2);
        if (parts.length != 2) {
            System.out.println("Invalid arguments. Usage: set <path> <newValue>");
            return;
        }

        String path = parts[0];
        String newValue = parts[1];

        JsonObject root = fileManager.getRoot();
        if (!(root instanceof JsonMap map)) {
            System.out.println("Root is not a valid JSON object.");
            return;
        }

        JsonObject existing = map.getByPath(path);
        if (existing == null) {
            System.out.println("Path not found: " + path);
            return;
        }

        map.setByPath(path, new JsonPrimitive(newValue));
        System.out.println("Value at path '" + path + "' updated to: " + newValue);
    }
}
