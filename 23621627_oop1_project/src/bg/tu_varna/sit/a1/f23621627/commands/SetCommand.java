package bg.tu_varna.sit.a1.f23621627.commands;

import bg.tu_varna.sit.a1.f23621627.core.FileManager;

/**
 * Command that sets or updates the value of a specified key in the currently opened file.
 */
public class SetCommand implements Command {
    private final FileManager fileManager;

    public SetCommand(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    /**
     * Executes the set command.
     * Updates the value at the specified path with the new string provided.
     * Prints error messages if no file is open, arguments are missing, or the key is not found.
     *
     * @param arguments the arguments specifying the path and the new value (format: "path string")
     */
    @Override
    public void execute(String arguments) {
        if (!fileManager.isFileOpen()) {
            System.out.println("No file is currently open.");
            return;
        }

        if (arguments == null || arguments.isBlank()) {
            System.out.println("Usage: set <path> <string>");
            return;
        }

        String[] parts = arguments.trim().split("\\s+", 2);
        if (parts.length < 2) {
            System.out.println("Please provide both <path> and <string>.");
            return;
        }

        String path = parts[0];
        String newValue = parts[1];

        if (!newValue.startsWith("\"") || !newValue.endsWith("\"")) {
            newValue = "\"" + newValue + "\"";
        }


        String[] keys = path.split("\\.");
        String lastKey = keys[keys.length - 1];

        String content = fileManager.getContent();

        String updatedContent = replaceValueAfterKey(content, lastKey, newValue);

        if (updatedContent == null) {
            System.out.println("Key not found: " + lastKey);
            return;
        }

        fileManager.setContent(updatedContent);
        System.out.println("Value updated successfully.");
    }

    /**
     * Replaces the value associated with the given key in the JSON content with a new value.
     *
     * @param content the JSON content as a string
     * @param key the key whose value should be replaced
     * @param newValue the new value to set (must include quotes if it's a string)
     * @return the updated JSON content or null if the key is not found
     */
    private String replaceValueAfterKey(String content, String key, String newValue) {
        String searchKey = "\"" + key + "\"";
        int keyPos = content.indexOf(searchKey);
        if (keyPos == -1)
            return null;

        int colonPos = content.indexOf(":", keyPos + searchKey.length());
        if (colonPos == -1)
            return null;

        int valStart = colonPos + 1;
        while (valStart < content.length() && Character.isWhitespace(content.charAt(valStart))) {
            valStart++;
        }

        int valEnd = valStart;  // to be visible for "after"
        boolean insideString = false;
        for (; valEnd < content.length(); valEnd++) {
            char c = content.charAt(valEnd);
            if (c == '"' && (valEnd == valStart || content.charAt(valEnd - 1) != '\\')) {
                insideString = !insideString;
            }
            if (!insideString && (c == ',' || c == '}' || c == ']')) {
                break;
            }
        }

        String before = content.substring(0, valStart);
        String after = content.substring(valEnd);

        return before + newValue + after;
    }
}
