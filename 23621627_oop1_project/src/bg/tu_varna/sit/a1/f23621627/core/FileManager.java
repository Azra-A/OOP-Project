package bg.tu_varna.sit.a1.f23621627.core;

import java.io.*;

public class FileManager {
    private String currentFilePath;
    private String fileContent;

    public void open(String filePath) {
        if (filePath == null) {
            System.out.println("No file path provided.");
            return;
        }

        File file = new File(filePath);
        StringBuilder contentBuilder = new StringBuilder();

        try {
            if (!file.exists()) {
                if (file.createNewFile()) {
                    fileContent = "{}";
                }
            } else {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        contentBuilder.append(line).append("\n");
                    }
                    fileContent = contentBuilder.toString().trim();
                }
            }

            currentFilePath = filePath;
            System.out.println("Successfully opened " + file.getName());
        } catch (IOException e) {
            System.out.println("Error opening file: " + e.getMessage());
        }
    }

    public void close() {
        if (currentFilePath == null) {
            System.out.println("No file is currently open.");
            return;
        }

        currentFilePath = null;
        fileContent = null;
        System.out.println("Successfully closed file.");
    }

    public void save(String optionalPath) {
        if (currentFilePath == null) {
            System.out.println("No file is currently open.");
            return;
        }

        String path = currentFilePath;
        if (optionalPath != null) {
            path = optionalPath;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            if (fileContent != null) {
                writer.write(fileContent);
            } else {
                writer.write("");
            }
            System.out.println("Successfully saved " + path);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    public void saveAs(String filePath) {
        if (filePath == null) {
            System.out.println("No path provided.");
            return;
        }
        save(filePath);
    }

    public String getContent() {
        return fileContent;
    }

    public String getCurrentFilePath() {
        return currentFilePath;
    }


    public void setContent(String content) {
        this.fileContent = content;
    }

    public boolean isFileOpen() {
        return currentFilePath != null;
    }
}
