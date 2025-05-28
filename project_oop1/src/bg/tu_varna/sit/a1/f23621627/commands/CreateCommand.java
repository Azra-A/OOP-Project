package bg.tu_varna.sit.a1.f23621627.commands;

import bg.tu_varna.sit.a1.f23621627.core.FileManager;
import bg.tu_varna.sit.a1.f23621627.json.*;

/**
 * Command that creates a new JSON element at the specified path.
 */
public class CreateCommand implements Command {
    private final FileManager fileManager;

    /**
     * Constructs a CreateCommand with a reference to the FileManager.
     *
     * @param fileManager the file manager handling the current JSON file
     */
    public CreateCommand(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    /**
     * Executes the create command with given arguments.
     * Expected format: create path value
     *
     * @param args the command arguments, including path and value
     */
    @Override
    public void execute(String args) {
        if (!fileManager.isFileLoaded()) {
            System.out.println("No file is currently open.");
            return;
        }

        if (args == null || args.isBlank()) {
            System.out.println("Usage: create <path> <value>");
            return;
        }

        String[] parts = args.split("\\s+", 2);
        if (parts.length < 2) {
            System.out.println("Invalid arguments. Usage: create <path> <value>");
            return;
        }

        String path = parts[0];
        String value = parts[1];

        execute(path, value);
    }

    /**
     * Executes the creation of a JSON value at the specified path.
     *
     * @param path  the dot-separated path where the value should be created
     * @param value the value to create at the given path
     */
    public void execute(String path, String value) {
        JsonObject root = fileManager.getRoot();

        if (!(root instanceof JsonMap)) {
            System.out.println("Root is not a JSON object.");
            return;
        }

        try {
            JsonPrimitive newValue = new JsonPrimitive(value);
            ((JsonMap) root).createByPath(path, newValue);
        } catch (Exception e) {
            System.out.println("Error creating value: " + e.getMessage());
        }
    }
}
