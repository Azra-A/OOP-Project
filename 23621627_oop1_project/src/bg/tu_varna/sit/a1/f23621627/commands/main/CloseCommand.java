package bg.tu_varna.sit.a1.f23621627.commands.main;

import bg.tu_varna.sit.a1.f23621627.commands.Command;
import bg.tu_varna.sit.a1.f23621627.core.FileManager;

/**
 * Command that closes the currently opened file.
 */
public class CloseCommand implements Command {

    private final FileManager fileManager;

    /**
     * Creates a new CloseCommand.
     *
     * @param fileManager used to manage file operations
     */
    public CloseCommand(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    /**
     * Executes the close command.
     *
     * @param arguments not used
     */
    @Override
    public void execute(String arguments) {
        fileManager.close();
    }
}
