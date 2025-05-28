package bg.tu_varna.sit.a1.f23621627.commands;

import bg.tu_varna.sit.a1.f23621627.core.FileManager;
import bg.tu_varna.sit.a1.f23621627.json.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Command that searches for all JSON elements with a specified key in the loaded JSON file
 * and prints their corresponding values.
 */
public class SearchCommand implements Command {
    private final FileManager fileManager;

    /**
     * Constructs a SearchCommand with the given FileManager.
     *
     * @param fileManager the FileManager managing the current JSON file
     */
    public SearchCommand(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    /**
     * Executes the search command.
     * Searches for all JSON elements with the specified key and prints their values.
     *
     * @param keyToFind the key to search for in the JSON structure
     */
    @Override
    public void execute(String keyToFind) {
        if (!fileManager.isFileLoaded()) {
            System.out.println("No file is currently open.");
            return;
        }

        if (keyToFind == null || keyToFind.isBlank()) {
            System.out.println("Usage: search <key>");
            return;
        }

        JsonObject root = fileManager.getRoot();
        if (!(root instanceof JsonMap map)) {
            System.out.println("Root is not a valid JSON object.");
            return;
        }

        List<String> foundValues = new ArrayList<>();
        searchByKey(map, keyToFind, foundValues);

        System.out.println("Search results:");
        if (foundValues.isEmpty()) {
            System.out.println("No matches found!");
        } else {
            for (String value : foundValues) {
                System.out.println("- " + value);
            }
        }
    }

    /**
     * Recursively searches the JSON structure for all entries with the specified key
     * and adds their JSON string representations to the foundValues list.
     *
     * @param current     the current JSON object to search within
     * @param keyToFind   the key to search for
     * @param foundValues the list collecting found values as JSON strings
     */
    private void searchByKey(JsonObject current, String keyToFind, List<String> foundValues) {
        if (current instanceof JsonMap map) {
            for (Map.Entry<String, JsonObject> entry : map.getMembers().entrySet()) {
                String key = entry.getKey();
                JsonObject val = entry.getValue();

                if (key.equals(keyToFind)) {
                    foundValues.add(val.toJsonString());
                }

                searchByKey(val, keyToFind, foundValues);
            }
        } else if (current instanceof JsonArray array) {
            for (JsonObject item : array.getElements()) {
                searchByKey(item, keyToFind, foundValues);
            }
        }
    }
}
