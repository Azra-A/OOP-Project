package bg.tu_varna.sit.a1.f23621627.core;

/**
 * Represents supported command types in the application.
 */
public enum CommandType {
    OPEN("open"),
    CLOSE("close"),
    SAVE("save"),
    SAVE_AS("saveas"),
    HELP("help"),
    EXIT("exit"),
    PRINT("print"),
    VALIDATE("validate"),
    SEARCH("search"),
    SET("set"),
    CREATE("create"),
    DELETE("delete"),
    MOVE("move");

    private final String inputText;

    /**
     * Creates a command type with the given text.
     *
     * @param inputText the command text
     */
    CommandType(String inputText) {
        this.inputText = inputText;
    }

    public String getInputText() {
        return inputText;
    }

    /**
     * Converts a string to a matching command type.
     *
     * @param input the input string
     * @return the matching command type, or null if not found
     */
    public static CommandType fromString(String input) {
        for (CommandType type : values()) {
            if (type.inputText.equalsIgnoreCase(input)) {
                return type;
            }
        }
        return null;
    }
}
