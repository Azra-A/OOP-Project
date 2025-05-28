package bg.tu_varna.sit.a1.f23621627.commands.main;

import bg.tu_varna.sit.a1.f23621627.core.FileManager;
import bg.tu_varna.sit.a1.f23621627.commands.Command;

/**
 * Command that closes the currently open file.
 */
public class CloseCommand implements Command {
    private final FileManager fileManager;

    /**
     * Constructs a CloseCommand with the given FileManager.
     *
     * @param fileManager the FileManager used to close the file
     */
    public CloseCommand(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    /**
     * Executes the close command by invoking the FileManager's close method.
     *
     * @param args unused argument
     */
    @Override
    public void execute(String args) {
        fileManager.close();
    }
}
