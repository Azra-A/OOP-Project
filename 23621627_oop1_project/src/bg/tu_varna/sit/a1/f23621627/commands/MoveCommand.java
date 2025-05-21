package bg.tu_varna.sit.a1.f23621627.commands;

import bg.tu_varna.sit.a1.f23621627.core.FileManager;

/**
 * Command that moves a JSON element from one path to another
 * within the currently opened file's content.
 */
public class MoveCommand implements Command {
    private final FileManager fileManager;

    public MoveCommand(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    /**
     * Executes the move command.
     * Expects two arguments: source path and destination path.
     * Moves the element from the source to the destination within the JSON.
     * Prints error messages if no file is open, arguments are invalid,
     * or if paths cannot be found or processed.
     *
     * @param arguments a string containing the source and destination paths separated by space
     */
    @Override
    public void execute(String arguments) {
        if (!fileManager.isFileOpen()) {
            System.out.println("No file is currently open.");
            return;
        }

        if (arguments == null || arguments.isBlank()) {
            System.out.println("Usage: move <from> <to>");
            return;
        }

        String[] parts = arguments.trim().split("\\s+");
        if (parts.length != 2) {
            System.out.println("Please provide both from and to paths.");
            return;
        }

        String fromPath = parts[0];
        String toPath = parts[1];

        String[] fromKeys = fromPath.split("\\.");
        String content = fileManager.getContent();

        int fromStart = findKeyIndex(content, fromKeys);
        if (fromStart == -1) {
            System.out.println("Error: 'from' path not found.");
            return;
        }

        int fromEnd = findValueEnd(content, fromStart);
        if (fromEnd == -1) {
            System.out.println("Error: Could not determine value bounds.");
            return;
        }

        String keyPart = content.substring(fromStart, fromEnd);
        int colonIndex = keyPart.indexOf(":");
        if (colonIndex == -1) {
            System.out.println("Error: Invalid value.");
            return;
        }

        String value = keyPart.substring(colonIndex + 1).trim();
        if (value.endsWith(",")) {
            value = value.substring(0, value.length() - 1).trim();
        }

        if (value.startsWith("\"") && value.endsWith("\"")) {
            value = value.substring(1, value.length() - 1);
        }

        // create the element at the path with the extracted value
        new CreateCommand(fileManager).execute(toPath + " " + value);

        // delete the element from the original location
        new DeleteCommand(fileManager).execute(fromPath);

        System.out.println("Element moved successfully.");
    }

    /**
     * Finds the starting index of the nested key path in the JSON content.
     *
     * @param content the JSON content as a string
     * @param keys    the array of keys representing the nested path
     * @return the index of the first key occurrence or -1 if not found
     */
    private int findKeyIndex(String content, String[] keys) {
        int index = 0;
        for (String key : keys) {
            String quoted = "\"" + key + "\"";   // in json key is " "
            index = content.indexOf(quoted, index);
            if (index == -1)
                return -1;
        }
        return index;
    }

    /**
     * Finds the end index of the value for the key starting at keyStart.
     * Accounts for quoted strings and nested structures.
     *
     * @param content  the JSON content as a string
     * @param keyStart the index where the key starts
     * @return the index just after the value or -1 if not found
     */
    private int findValueEnd(String content, int keyStart) {
        int colonIndex = content.indexOf(":", keyStart);
        if (colonIndex == -1) return -1;

        int i = colonIndex + 1;
        boolean inQuotes = false;

        while (i < content.length()) {
            char c = content.charAt(i);

            // symbol is " and is unescaped with \\
            if (c == '"' && (i == 0 || content.charAt(i - 1) != '\\')) {
                inQuotes = !inQuotes;
            }

            // value is found
            if (!inQuotes && (c == ',' || c == '\n' || c == '}')) {
                return i;
            }
            i++;
        }

        return i;
    }
}
