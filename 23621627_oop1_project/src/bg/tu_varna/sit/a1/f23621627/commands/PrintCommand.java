package bg.tu_varna.sit.a1.f23621627.commands;

import bg.tu_varna.sit.a1.f23621627.core.FileManager;

public class PrintCommand implements Command {
    private final FileManager fileManager;

    public PrintCommand(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String arguments) {
        if (!fileManager.isFileOpen()) {
            System.out.println("No file is currently open.");
            return;
        }

        String content = fileManager.getContent();
        if (content == null || content.isBlank()) {
            System.out.println("File is empty.");
            return;
        }

        try {
            String printText = formatJson(content);
            System.out.println(printText);
        } catch (Exception e) {
            System.out.println("Failed to format JSON: " + e.getMessage());
        }
    }

    private String formatJson(String json) {
        StringBuilder result = new StringBuilder();
        int indent = 0;
        boolean inString = false;
        boolean lastCharWasNewline = false;

        for (int i = 0; i < json.length(); i++) {
            char c = json.charAt(i);

            switch (c) {
                case '"':
                    result.append(c);
                    if (i > 0 && json.charAt(i - 1) != '\\') {
                        inString = !inString;
                    }
                    lastCharWasNewline = false;
                    break;

                case '{':
                case '[':
                    result.append(c);
                    if (!inString) {
                        indent++;
                        result.append("\n");
                        result.append("  ".repeat(indent));
                        lastCharWasNewline = true;
                    }
                    break;

                case '}':
                case ']':
                    if (!inString) {
                        indent--;
                        result.append("\n");
                        result.append("  ".repeat(indent));
                    }
                    result.append(c);
                    lastCharWasNewline = false;
                    break;

                case ',':
                    result.append(c);
                    if (!inString) {
                        result.append("\n");
                        result.append("  ".repeat(indent));
                        lastCharWasNewline = true;
                    }
                    break;

                case ':':
                    result.append(c);
                    if (!inString) {
                        result.append(" ");
                        lastCharWasNewline = false;
                    }
                    break;

                case '\n':
                case '\r':
                    break;

                default:
                    if (lastCharWasNewline && c == ' ') {
                        break;
                    }
                    result.append(c);
                    lastCharWasNewline = false;
                    break;
            }
        }

        String[] lines = result.toString().split("\n");
        StringBuilder cleaned = new StringBuilder();
        boolean lastLineEmpty = false;

        for (String line : lines) {
            if (line.trim().isEmpty()) {
                if (!lastLineEmpty) {
                    cleaned.append("\n");
                    lastLineEmpty = true;
                }
            } else {
                cleaned.append(line).append("\n");
                lastLineEmpty = false;
            }
        }

        return cleaned.toString().trim();
    }

}
