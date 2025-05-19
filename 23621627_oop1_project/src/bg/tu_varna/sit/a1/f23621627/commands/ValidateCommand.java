package bg.tu_varna.sit.a1.f23621627.commands;

import bg.tu_varna.sit.a1.f23621627.core.FileManager;

public class ValidateCommand implements Command {
    private final FileManager fileManager;

    public ValidateCommand(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String arguments) {
        if (!fileManager.isFileOpen()) {
            System.out.println("No file is currently open.");
            return;
        }

        if (fileManager.getCurrentFilePath() == null || !fileManager.getCurrentFilePath().toLowerCase().endsWith(".json")) {
            System.out.println("Invalid JSON file.");
            return;
        }

        String content = fileManager.getContent();
        if (content == null || content.isBlank() || content.trim().equals("{}")) {
            System.out.println("File is empty.");
            return;
        }

        if (validateJson(content)) {
            System.out.println("JSON is valid.");
        } else {
            System.out.println("Invalid JSON syntax.");
        }

    }

    private boolean validateJson(String json) {
        int curlyBraces = 0;
        int squareBrackets = 0;
        boolean insideMainBraces = false;
        boolean contentOutsideMainBraces = false;

        for (int i = 0; i < json.length(); i++) {
            char c = json.charAt(i);

            switch (c) {
                case '{':
                    curlyBraces++;
                    if (curlyBraces == 1 && !insideMainBraces)
                        insideMainBraces = true;
                    break;

                case '}':
                    curlyBraces--;
                    if (curlyBraces == 0 && insideMainBraces)
                        insideMainBraces = false;
                    else if (curlyBraces < 0)
                        return false;
                    break;

                case '[':
                    squareBrackets++;
                    break;

                case ']':
                    squareBrackets--;
                    if (squareBrackets < 0)
                        return false;
                    break;

                default:
                    if (!Character.isWhitespace(c) && !insideMainBraces)
                        contentOutsideMainBraces = true;
                    break;
            }
        }

        return curlyBraces == 0 && squareBrackets == 0 && !contentOutsideMainBraces;
    }



}

