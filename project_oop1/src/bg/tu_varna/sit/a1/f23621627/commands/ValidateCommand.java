package bg.tu_varna.sit.a1.f23621627.commands;

import bg.tu_varna.sit.a1.f23621627.core.FileManager;
import bg.tu_varna.sit.a1.f23621627.json.JsonObject;
import bg.tu_varna.sit.a1.f23621627.json.SimpleJsonParser;

/**
 * Command to validate whether the currently loaded JSON file contains valid JSON syntax.
 */
public class ValidateCommand implements Command {
    private final FileManager fileManager;

    /**
     * Constructs a ValidateCommand with the specified FileManager.
     *
     * @param fileManager the file manager responsible for handling the JSON file
     */
    public ValidateCommand(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    /**
     * Executes the validation command.
     * Checks if the loaded file content is valid JSON.
     *
     * @param args unused parameter for this command
     */
    @Override
    public void execute(String args) {
        if (!fileManager.isFileLoaded()) {
            System.out.println("No file is currently open.");
            return;
        }

        String path = fileManager.getCurrentFilePath();
        if (path == null || !path.toLowerCase().endsWith(".json")) {
            System.out.println("The loaded file is not a JSON file.");
            return;
        }

        String content = fileManager.getFileContent();

        JsonObject parsed = SimpleJsonParser.parse(content);

        if (parsed == null) {
            System.out.println("Invalid JSON.");
        } else {
            System.out.println("The JSON file is valid.");
        }
    }
}
