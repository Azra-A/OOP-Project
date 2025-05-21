package bg.tu_varna.sit.a1.f23621627.commands;

import bg.tu_varna.sit.a1.f23621627.core.FileManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Command that searches for all values associated with a specified key in the currently opened file.
 * Prints all found values or an appropriate message if no values are found.
 */
public class SearchCommand implements Command {
    private final FileManager fileManager;

    public SearchCommand(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    /**
     * Executes the search command.
     * Searches for the provided key in the JSON content of the opened file and prints all values found.
     * Prints error messages if no file is open, no key is provided, or the file is empty.
     *
     * @param arguments the key to search for in the file content
     */
    @Override
    public void execute(String arguments) {
        if (!fileManager.isFileOpen()) {
            System.out.println("No file is currently open.");
            return;
        }

        if (arguments == null || arguments.isBlank()) {
            System.out.println("Please provide a key to search.");
            return;
        }

        String key = arguments.trim().replace("\"", "");
        String content = fileManager.getContent();

        if (content == null || content.isBlank()) {
            System.out.println("File is empty.");
            return;
        }

        List<String> values = extractValuesForKey(content, key);

        if (values.isEmpty()) {
            System.out.println("No data found for key: " + key);
        } else {
            System.out.println("Found values for key '" + key + "':");
            values.forEach(System.out::println);
        }
    }

    /**
     * Extracts all values for a specified key from a JSON string.
     *
     * @param json the JSON content as a string
     * @param key the key to search for
     * @return a list of all values associated with the key
     */
    private List<String> extractValuesForKey(String json, String key) {
        List<String> results = new ArrayList<>();
        String searchKey = "\"" + key + "\"";
        int index = 0;

        // searches for "key" in file
        while ((index = json.indexOf(searchKey, index)) != -1) {
            int colon = json.indexOf(':', index + searchKey.length());
            if (colon == -1)
                break;

            int valStart = colon + 1;
            while (valStart < json.length() && Character.isWhitespace(json.charAt(valStart))) {
                valStart++;
            }
            if (valStart >= json.length())
                break;

            String value = extractValue(json, valStart);
            if (value != null) {
                results.add(value);
            }

            index = valStart;
        }

        return results;
    }

    /**
     * Extracts a single JSON value starting at a given index.
     * Supports strings, objects, arrays, and primitive values.
     *
     * @param json the JSON content
     * @param start the start index of the value
     * @return the extracted value as a string, or null if extraction fails
     */
    private String extractValue(String json, int start) {
        char first = json.charAt(start);
        int end = start;

        switch (first) {
            case '"':
                end = start + 1;
                while (end < json.length()) {
                    if (json.charAt(end) == '"' && json.charAt(end - 1) != '\\')
                        break;
                    end++;
                }
                return json.substring(start + 1, end);
            case '{':
            case '[':
                char close;
                if (first == '{') {
                    close = '}';
                } else {
                    close = ']';
                }

                int count = 1;
                end = start + 1;
                while (end < json.length() && count > 0) {
                    if (json.charAt(end) == first)
                        count++;
                    else if (json.charAt(end) == close)
                        count--;
                    end++;
                }
                return json.substring(start, end);
            default:
                // end - not ,}]
                while (end < json.length() && ",}]".indexOf(json.charAt(end)) == -1)
                    end++;

                return json.substring(start, end).trim();
        }
    }
}
