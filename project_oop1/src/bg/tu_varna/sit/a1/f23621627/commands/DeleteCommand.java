package bg.tu_varna.sit.a1.f23621627.commands;

import bg.tu_varna.sit.a1.f23621627.core.FileManager;
import bg.tu_varna.sit.a1.f23621627.json.JsonMap;
import bg.tu_varna.sit.a1.f23621627.json.JsonObject;

/**
 * Command that deletes a JSON element at the specified path.
 */
public class DeleteCommand implements Command {
    private final FileManager fileManager;

    /**
     * Constructs a DeleteCommand with the provided FileManager instance.
     *
     * @param fileManager the file manager responsible for managing the current JSON file
     */
    public DeleteCommand(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    /**
     * Executes the delete command.
     * Expected usage: delete path
     *
     * @param args the path to the JSON element to be deleted
     */
    @Override
    public void execute(String args) {
        if (!fileManager.isFileLoaded()) {
            System.out.println("No file is currently open.");
            return;
        }

        if (args == null || args.isBlank()) {
            System.out.println("Usage: delete <path>");
            return;
        }

        JsonObject root = fileManager.getRoot();

        if (!(root instanceof JsonMap)) {
            System.out.println("Root JSON is not a map.");
            return;
        }

        try {
            ((JsonMap) root).deleteByPath(args);
        } catch (Exception e) {
            System.out.println("Error deleting element: " + e.getMessage());
        }
    }
}
