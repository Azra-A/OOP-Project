package bg.tu_varna.sit.a1.f23621627.core;

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

    CommandType(String inputText) {
        this.inputText = inputText;
    }

    public String getInputText() {
        return inputText;
    }

    public static CommandType fromString(String input) {
        for (CommandType type : values()) {
            if (type.inputText.equalsIgnoreCase(input)) {
                return type;
            }
        }
        return null;
    }
}
