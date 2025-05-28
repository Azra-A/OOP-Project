package bg.tu_varna.sit.a1.f23621627.commands.main;

import bg.tu_varna.sit.a1.f23621627.core.FileManager;
import bg.tu_varna.sit.a1.f23621627.commands.Command;

/**
 * Command that saves the currently loaded JSON file.
 */
public class SaveCommand implements Command {
    private final FileManager fileManager;

    /**
     * Constructs a new SaveCommand with the specified FileManager.
     *
     * @param fileManager the file manager used to save the file
     */
    public SaveCommand(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    /**
     * Executes the save command by saving the current JSON file.
     *
     * @param args not used for this command
     */
    @Override
    public void execute(String args) {
        fileManager.save();
    }
}
