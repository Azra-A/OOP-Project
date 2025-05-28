package bg.tu_varna.sit.a1.f23621627.commands.main;

import bg.tu_varna.sit.a1.f23621627.core.FileManager;
import bg.tu_varna.sit.a1.f23621627.commands.Command;

/**
 * Command that saves the currently loaded JSON to a new file.
 */
public class SaveAsCommand implements Command {
    private final FileManager fileManager;

    /**
     * Constructs a new SaveAsCommand with the specified FileManager.
     *
     * @param fileManager the file manager used to save the file
     */
    public SaveAsCommand(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    /**
     * Executes the saveas command by saving the JSON to the specified file path.
     *
     * @param args the new file path where the JSON will be saved
     */
    @Override
    public void execute(String args) {
        if (args == null) {
            System.out.println("No file path provided.");
            return;
        }
        fileManager.saveAs(args.trim());
    }
}
