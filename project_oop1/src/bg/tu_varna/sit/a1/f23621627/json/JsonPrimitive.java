package bg.tu_varna.sit.a1.f23621627.json;

/**
 * Represents a JSON primitive value (string, number, boolean, or null).
 */
public class JsonPrimitive extends JsonObject {
    private final String value;

    /**
     * Constructs a JsonPrimitive with the given string value.
     *
     * @param value the primitive value as a string
     */
    public JsonPrimitive(String value) {
        this.value = value;
    }

    /**
     * Returns the JSON string representation of this primitive,
     * including quotes around the value.
     *
     * @param indent the indentation level (ignored for primitives)
     * @return the JSON string of the primitive
     */
    @Override
    public String toJsonString(int indent) {
        return "\"" + value + "\"";
    }

    /**
     * Returns null since primitives do not have child elements.
     *
     * @param path the path in the JSON structure (ignored)
     * @return null always
     */
    @Override
    public JsonObject getByPath(String path) {
        return null;
    }

    /**
     * Does nothing since primitives cannot have children.
     *
     * @param path the path in the JSON structure (ignored)
     * @param value the value to set (ignored)
     */
    @Override
    public void setByPath(String path, JsonObject value) {}

    /**
     * Does nothing since primitives cannot have children.
     *
     * @param path the path in the JSON structure (ignored)
     * @param value the value to create (ignored)
     */
    @Override
    public void createByPath(String path, JsonObject value) {}

    /**
     * Does nothing since primitives cannot have children.
     *
     * @param path the path in the JSON structure (ignored)
     */
    @Override
    public void deleteByPath(String path) {}

    /**
     * Returns the raw value of this JSON primitive.
     *
     * @return the primitive value as a string
     */
    public String getValue() {
        return value;
    }

}
