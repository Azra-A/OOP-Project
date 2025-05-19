package bg.tu_varna.sit.a1.f23621627.commands;

import bg.tu_varna.sit.a1.f23621627.core.FileManager;

public class CreateCommand implements Command {
    private final FileManager fileManager;

    public CreateCommand(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String arguments) {
        if (!fileManager.isFileOpen()) {
            System.out.println("No file is currently open.");
            return;
        }

        if (arguments == null || arguments.isBlank()) {
            System.out.println("Usage: create <path> <value>");
            return;
        }

        String[] parts = arguments.trim().split("\\s+", 2);
        if (parts.length < 2) {
            System.out.println("Please provide both path and value.");
            return;
        }

        String path = parts[0];
        String value = parts[1];

        String[] keys = path.split("\\.");
        String lastKey = keys[keys.length - 1];
        String content = fileManager.getContent();



        if (content.isEmpty() || content.equals("{}")) {
            StringBuilder newContent = new StringBuilder();
            int indentLevel = keys.length;
            String indent = "  ".repeat(indentLevel - 1);

            for (int i = 0; i < keys.length - 1; i++) {
                newContent.append(indent).append("\"").append(keys[i]).append("\": {\n");
                indent += "  ";
            }
            newContent.append(indent).append("\"").append(lastKey).append("\": \"").append(value).append("\"\n");

            for (int i = keys.length - 2; i >= 0; i--) {
                indent = indent.substring(0, indent.length() - 2);
                newContent.append(indent).append("}");
                newContent.append("\n");
            }

            String finalContent = "{\n" + newContent.toString() + "}";
            fileManager.setContent(finalContent);
            System.out.println("Key added successfully.");
            return;
        }



        if (content.contains(lastKey + ":")) {
            System.out.println("Error: Key already exists: " + lastKey);
            return;
        }

        int insertIndex = findInsertIndex(content, keys);
        if (insertIndex == -1) {
            System.out.println("Error: Could not locate the path.");
            return;
        }

        int indentLevel = keys.length;
        String indent = "  ".repeat(indentLevel);
        String toInsert = ",\n" + indent + "\"" + lastKey + "\": \"" + value + "\"";

        StringBuilder updated = new StringBuilder(content);
        updated.insert(insertIndex, toInsert);

        fileManager.setContent(updated.toString());
        System.out.println("Key added successfully.");
    }

    private int findInsertIndex(String content, String[] keys) {
        int index = 0;

        for (int i = 0; i < keys.length - 1; i++) {
            String key = keys[i];
            int keyPos = content.indexOf("\"" + key + "\"", index);
            if (keyPos == -1) return -1;

            int braceStart = content.indexOf("{", keyPos);
            if (braceStart == -1) return -1;

            index = braceStart + 1;

        }

        int insertPos = index;
        int openBraces = 1;

        while (insertPos < content.length()) {
            char c = content.charAt(insertPos);
            if (c == '{') {
                openBraces++;
            } else if (c == '}') {
                openBraces--;
            }

            if (openBraces == 0)
                break;

            insertPos++;
        }

        return insertPos;
    }


}
