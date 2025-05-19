package bg.tu_varna.sit.a1.f23621627.commands;

import bg.tu_varna.sit.a1.f23621627.core.FileManager;

public class DeleteCommand implements Command {
    private final FileManager fileManager;

    public DeleteCommand(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String arguments) {
        if (!fileManager.isFileOpen()) {
            System.out.println("No file is currently open.");
            return;
        }

        if (arguments == null || arguments.isBlank()) {
            System.out.println("Usage: delete <path>");
            return;
        }

        String[] keys = arguments.trim().split("\\.");
        String content = fileManager.getContent();

        int startIndex = findKeyIndex(content, keys);
        if (startIndex == -1) {
            System.out.println("Error: Path not found.");
            return;
        }

        int endIndex = findValueEnd(content, startIndex);
        if (endIndex == -1) {
            System.out.println("Error: Could not determine value bounds.");
            return;
        }

        while (endIndex < content.length() && Character.isWhitespace(content.charAt(endIndex))) {
            endIndex++;
        }
        if (endIndex < content.length() && content.charAt(endIndex) == ',') {
            endIndex++;
        }

        StringBuilder updated = new StringBuilder(content);
        updated.delete(startIndex, endIndex);
        fileManager.setContent(updated.toString());

        // Премахване на запетая преди затваряща скоба
        String cleaned = updated.toString().replaceAll(",\\s*(\\}|\\])", "$1");
        fileManager.setContent(cleaned);

        System.out.println("Key deleted successfully.");
    }

    private int findKeyIndex(String content, String[] keys) {
        int index = 0;
        for (int i = 0; i < keys.length; i++) {
            String key = "\"" + keys[i] + "\"";
            index = content.indexOf(key, index);
            if (index == -1)
                return -1;
        }
        return index;
    }


    private int findValueEnd(String content, int keyStart) {
        int colonIndex = content.indexOf(":", keyStart);
        if (colonIndex == -1)
            return -1;

        int i = colonIndex + 1;
        boolean inQuotes = false;

        while (i < content.length()) {
            char c = content.charAt(i);

            if (c == '"' && (i == 0 || content.charAt(i - 1) != '\\')) {
                inQuotes = !inQuotes;
            }

            if (!inQuotes && (c == ',' || c == '\n' || c == '}')) {
                return i;
            }
            i++;
        }

        return i;
    }
}
