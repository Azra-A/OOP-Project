package bg.tu_varna.sit.a1.f23621627.commands;

import bg.tu_varna.sit.a1.f23621627.core.FileManager;
import bg.tu_varna.sit.a1.f23621627.json.JsonObject;

/**
 * Command that prints the JSON content of the currently loaded file to the console.
 */
public class PrintCommand implements Command {
    private final FileManager fileManager;

    /**
     * Constructs a PrintCommand with the given FileManager.
     *
     * @param fileManager the FileManager managing the current JSON file
     */
    public PrintCommand(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    /**
     * Executes the print command.
     * Prints the entire JSON content of the loaded file to the standard output.
     *
     * @param args optional arguments (ignored in this command)
     */
    @Override
    public void execute(String args) {
        if (!fileManager.isFileLoaded()) {
            System.out.println("No file is currently open.");
            return;
        }

        JsonObject root = fileManager.getRoot();

        if(root == null) {
            System.out.println("File is empty or there is invalid syntax that cannot be printed. Only json format is allowed to print!");
        } else {
            System.out.println(root.toJsonString(0));

        }

    }
}
