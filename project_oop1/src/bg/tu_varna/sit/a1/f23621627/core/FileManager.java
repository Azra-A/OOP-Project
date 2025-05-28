package bg.tu_varna.sit.a1.f23621627.core;

import bg.tu_varna.sit.a1.f23621627.json.JsonMap;
import bg.tu_varna.sit.a1.f23621627.json.JsonObject;
import bg.tu_varna.sit.a1.f23621627.json.SimpleJsonParser;

import java.io.*;
import java.nio.file.*;

/**
 * Manages file operations including opening, saving,
 * and closing JSON files and storing the parsed content.
 */
public class FileManager {
    private String currentFilePath = null;
    private JsonObject root = null;
    private boolean fileLoaded = false;
    private String fileContent = "";

    /**
     * Opens a file at the given path. If it doesn't exist,
     * creates an empty JSON map.
     *
     * @param path the path to the file
     * @return true if opened or created successfully, false otherwise
     */
    public boolean open(String path) {
        try {
            Path filePath = Paths.get(path);

            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
                root = new JsonMap();
            } else {
                StringBuilder contentBuilder = new StringBuilder();
                try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        contentBuilder.append(line);
                    }
                }
                fileContent = contentBuilder.toString().trim();
                root = SimpleJsonParser.parse(fileContent);
            }

            currentFilePath = path;
            fileLoaded = true;
            System.out.println("Successfully opened " + filePath.getFileName());
            return true;

        } catch (IOException e) {
            System.out.println("Error opening file: " + e.getMessage());
            return false;
        }
    }

    /**
     * Closes the currently opened file.
     */
    public void close() {
        if (currentFilePath == null) {
            System.out.println("No file is currently open.");
            return;
        }

        currentFilePath = null;
        root = null;
        fileLoaded = false;
        System.out.println("Successfully closed file.");
    }

    /**
     * Saves the current JSON content to the opened file.
     *
     * @return true if saved successfully, false otherwise
     */
    public boolean save() {
        if (!fileLoaded || currentFilePath == null) {
            System.out.println("No file is currently open.");
            return false;
        }

        try (FileWriter writer = new FileWriter(currentFilePath)) {
            writer.write(root.toJsonString());
            System.out.println("Successfully saved " + currentFilePath);
            return true;
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
            return false;
        }
    }

    /**
     * Saves the current JSON content to a new file.
     *
     * @param newPath the new file path
     * @return true if saved successfully, false otherwise
     */
    public boolean saveAs(String newPath) {
        try (FileWriter writer = new FileWriter(newPath)) {
            writer.write(root.toJsonString());
            System.out.println("Successfully saved " + newPath);
            return true;
        } catch (IOException e) {
            System.out.println("Error saving as: " + e.getMessage());
            return false;
        }
    }

    /**
     * Checks if a file is currently loaded.
     *
     * @return true if a file is loaded, false otherwise
     */
    public boolean isFileLoaded() {
        return fileLoaded;
    }

    public JsonObject getRoot() {
        return root;
    }

    public String getCurrentFilePath() {
        return currentFilePath;
    }

    public String getFileContent() {
        return fileContent;
    }
}
