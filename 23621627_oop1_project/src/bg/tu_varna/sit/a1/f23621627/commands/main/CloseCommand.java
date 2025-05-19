package bg.tu_varna.sit.a1.f23621627.commands.main;

import bg.tu_varna.sit.a1.f23621627.commands.Command;
import bg.tu_varna.sit.a1.f23621627.core.FileManager;

public class CloseCommand implements Command {
    private final FileManager fileManager;

    public CloseCommand(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String arguments) {
        fileManager.close();
    }
}
