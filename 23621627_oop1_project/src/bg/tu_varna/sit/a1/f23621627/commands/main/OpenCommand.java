package bg.tu_varna.sit.a1.f23621627.commands.main;

import bg.tu_varna.sit.a1.f23621627.commands.Command;
import bg.tu_varna.sit.a1.f23621627.core.FileManager;

/**
 * Executes the command to open a file using the FileManager.
 */
public class OpenCommand implements Command {
    private final FileManager fileManager;

    public OpenCommand(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    /**
     * Opens a file specified by the arguments.
     *
     * @param arguments the file path to open
     */
    @Override
    public void execute(String arguments) {
        fileManager.open(arguments);
    }
}
