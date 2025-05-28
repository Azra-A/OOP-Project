package bg.tu_varna.sit.a1.f23621627.commands.main;

import bg.tu_varna.sit.a1.f23621627.core.FileManager;
import bg.tu_varna.sit.a1.f23621627.commands.Command;

/**
 * Command that opens a JSON file by the given path,
 * or creates a new file if it does not exist.
 */
public class OpenCommand implements Command {
    private final FileManager fileManager;

    /**
     * Constructs a new OpenCommand with the specified FileManager.
     *
     * @param fileManager the file manager used to open the file
     */
    public OpenCommand(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    /**
     * Executes the open command by attempting to open the file at the given path.
     *
     * @param args the file path to open
     */
    @Override
    public void execute(String args) {
        if (args == null) {
            System.out.println("No file path provided.");
            return;
        }
        fileManager.open(args.trim());
    }
}
