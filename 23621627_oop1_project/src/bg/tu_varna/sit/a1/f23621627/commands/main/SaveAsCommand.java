package bg.tu_varna.sit.a1.f23621627.commands.main;

import bg.tu_varna.sit.a1.f23621627.commands.Command;
import bg.tu_varna.sit.a1.f23621627.core.FileManager;

/**
 * Command to save the currently opened file to a new location.
 */
public class SaveAsCommand implements Command {
    private final FileManager fileManager;

    public SaveAsCommand(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    /**
     * Saves the current file content to a new file specified by the argument.
     * Prints messages if no file is open or no path is provided.
     *
     * @param arguments the new file path to save as
     */
    @Override
    public void execute(String arguments) {
        if (!fileManager.isFileOpen()) {
            System.out.println("No file is currently open.");
            return;
        }

        if (arguments == null || arguments.isBlank()) {
            System.out.println("No path specified.");
            return;
        }

        fileManager.saveAs(arguments.trim());
    }
}
