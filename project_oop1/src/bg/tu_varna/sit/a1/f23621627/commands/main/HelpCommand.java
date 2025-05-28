package bg.tu_varna.sit.a1.f23621627.commands.main;

import bg.tu_varna.sit.a1.f23621627.commands.Command;

/**
 * Command that displays a list of available commands and their descriptions.
 */
public class HelpCommand implements Command {

    /**
     * Executes the help command by printing all supported commands and their usage.
     *
     * @param args unused argument
     */
    @Override
    public void execute(String args) {
        System.out.println(" open <path> - Opens file. If there's no file with given path, it creates a new file.");
        System.out.println(" close - Closes current file.");
        System.out.println(" validate - Checks if the JSON file is valid.");
        System.out.println(" print - Displays the contents of the JSON file.");
        System.out.println(" search <key> - Searches for values by a given key.");
        System.out.println(" set <path> <value> - Sets a new JSON element value.");
        System.out.println(" create <path> <value> - Creates a new JSON element.");
        System.out.println(" delete <path> - Deletes a JSON element.");
        System.out.println(" move <from> <to> - Moves JSON element.");
        System.out.println(" save - Saves the JSON file.");
        System.out.println(" saveas <file> - Saves the JSON to a new file.");
        System.out.println(" help - Displays this information.");
        System.out.println(" exit - Exits the program.");
    }
}
