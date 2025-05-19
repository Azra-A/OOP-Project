package bg.tu_varna.sit.a1.f23621627.commands.main;

import bg.tu_varna.sit.a1.f23621627.commands.Command;
import bg.tu_varna.sit.a1.f23621627.core.FileManager;

/**
 * Command to save the currently opened file.
 * Does not accept a file path argument.
 */
public class SaveCommand implements Command {
    private final FileManager fileManager;

    public SaveCommand(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    /**
     * Saves the current file content to the opened file.
     * If arguments are provided, prints an error message advising to use 'save as'.
     *
     * @param arguments optional arguments (ignored for this command)
     */
    @Override
    public void execute(String arguments) {
        if (!fileManager.isFileOpen()) {
            System.out.println("No file is currently open.");
            return;
        }

        if (arguments == null || arguments.isBlank()) {
            fileManager.save(null);
        } else {
            System.out.println("Can not enter path when using save. Try 'save as' to enter a new path.");
        }
    }
}
