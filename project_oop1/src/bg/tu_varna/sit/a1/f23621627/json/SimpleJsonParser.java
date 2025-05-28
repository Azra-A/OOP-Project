package bg.tu_varna.sit.a1.f23621627.json;

/**
 * A simple JSON parser that converts JSON strings into JsonObject instances.
 */
public class SimpleJsonParser {
    private static int index;
    private static String json;

    /**
     * Parses a JSON string and returns the root JsonObject.
     *
     * @param input the JSON string to parse
     * @return the parsed JsonObject
     */
    public static JsonObject parse(String input) {
        json = input.trim();
        index = 0;
        skipWhitespace();
        return parseValue();
    }

    /**
     * Parses a JSON value based on the current character.
     *
     * @return the parsed JsonObject
     */
    private static JsonObject parseValue() {
        skipWhitespace();
        char c = peek();
        if (c == '{')
            return parseObject();
        if (c == '[')
            return parseArray();
        if (c == '"')
            return new JsonPrimitive(parseString());

        if (Character.isDigit(c) || c == '-')
            return new JsonPrimitive(parseNumber());
        if (json.startsWith("true", index)) {
            index += 4;
            return new JsonPrimitive("true");
        }
        if (json.startsWith("false", index)) {
            index += 5;
            return new JsonPrimitive("false");
        }
        if (json.startsWith("null", index)) {
            index += 4;
            return new JsonPrimitive("null");
        }
        System.out.println("Invalid JSON value at position " + index);
        return null;
    }

    /**
     * Parses a JSON object from the current position.
     *
     * @return the parsed JsonMap
     */
    private static JsonMap parseObject() {
        JsonMap obj = new JsonMap();
        expect('{');
        skipWhitespace();
        while (peek() != '}') {
            String key = parseString();
            skipWhitespace();
            expect(':');
            skipWhitespace();
            JsonObject value = parseValue();
            obj.put(key, value);
            skipWhitespace();
            if (peek() == ',') {
                index++;
                skipWhitespace();
            } else {
                break;
            }
        }
        expect('}');
        return obj;
    }

    /**
     * Parses a JSON array from the current position.
     *
     * @return the parsed JsonArray
     */
    private static JsonArray parseArray() {
        JsonArray array = new JsonArray();
        expect('[');
        skipWhitespace();
        while (peek() != ']') {
            JsonObject value = parseValue();
            array.add(value);
            skipWhitespace();
            if (peek() == ',') {
                index++;
                skipWhitespace();
            } else {
                break;
            }
        }
        expect(']');
        return array;
    }

    /**
     * Parses a JSON string from the current position.
     *
     * @return the parsed string
     */
    private static String parseString() {
        expect('"');
        StringBuilder sb = new StringBuilder();
        while (peek() != '"') {
            sb.append(json.charAt(index++));
        }
        expect('"');
        return sb.toString();
    }

    /**
     * Parses a JSON number from the current position.
     *
     * @return the parsed number as a string
     */
    private static String parseNumber() {
        StringBuilder sb = new StringBuilder();
        while (index < json.length() &&
                (Character.isDigit(json.charAt(index)) || json.charAt(index) == '-' || json.charAt(index) == '.')) {
            sb.append(json.charAt(index++));
        }
        return sb.toString();
    }

    /**
     * Skips any whitespace characters at the current position.
     */
    private static void skipWhitespace() {
        while (index < json.length() && Character.isWhitespace(json.charAt(index)))
            index++;
    }

    /**
     * Returns the current non-whitespace character without advancing the index.
     *
     * @return the current character
     */
    private static char peek() {
        skipWhitespace();
        if (index >= json.length()) {
            System.out.println("Unexpected end of input.");
        }
        return json.charAt(index);
    }

    /**
     * Verifies and consumes the expected character.
     *
     * @param expected the character to expect at the current position
     */
    private static void expect(char expected) {
        if (peek() != expected) {
            System.out.println("Expected '" + expected + "' at position " + index);
        }
        index++;
    }
}
