package bg.tu_varna.sit.a1.f23621627.commands;

import bg.tu_varna.sit.a1.f23621627.core.FileManager;

/**
 * Command implementation that validates the syntax of a JSON file
 * currently opened in the FileManager.
 */
public class ValidateCommand implements Command {
    private final FileManager fileManager;

    public ValidateCommand(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    /**
     * Executes the validation command.
     *
     * @param arguments optional arguments for the command (not used here)
     */
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

    /**
     * Validates the JSON string by checking balanced braces, brackets, quotes,
     * and ensuring no content exists outside the main braces.
     *
     * @param json the JSON content to validate
     * @return true if the JSON content is valid; false otherwise
     */
    private boolean validateJson(String json) {
        int curlyBraces = 0;
        int squareBrackets = 0;
        boolean insideMainBraces = false;
        boolean contentOutsideMainBraces = false;

        int quoteCount = 0;  // Counts unescaped double quotes

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

                case '"':
                    if (i == 0 || json.charAt(i - 1) != '\\') {
                        quoteCount++;
                    }
                    break;

                default:
                    if (!Character.isWhitespace(c) && !insideMainBraces)
                        contentOutsideMainBraces = true;
                    break;
            }
        }

        // valid only if
        // - all curly braces and square brackets are balanced
        // - no content outside main braces
        // - quotes count is even (balanced)

        boolean bracesBalanced = (curlyBraces == 0);
        boolean bracketsBalanced = (squareBrackets == 0);
        boolean quotesBalanced = (quoteCount % 2 == 0);

        return bracesBalanced && bracketsBalanced && !contentOutsideMainBraces && quotesBalanced;
    }
}
