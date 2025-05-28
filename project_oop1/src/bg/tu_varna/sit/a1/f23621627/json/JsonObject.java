package bg.tu_varna.sit.a1.f23621627.json;

/**
 * Abstract class representing a JSON object with methods
 * for serialization and path-based manipulation.
 */
public abstract class JsonObject {

    /**
     * Returns the JSON string with the specified indentation.
     *
     * @param indent the level of indentation (number of spaces)
     * @return formatted JSON string
     */
    public abstract String toJsonString(int indent);

    /**
     * Returns the JSON string without indentation.
     *
     * @return JSON string
     */
    public String toJsonString() {
        return toJsonString(0);
    }

    /**
     * Gets a JSON object by the given path.
     *
     * @param path the path in the JSON structure
     * @return the JSON object at the specified path
     */
    public abstract JsonObject getByPath(String path);

    /**
     * Sets a JSON object value at the given path.
     *
     * @param path the path in the JSON structure
     * @param value the new JSON object value to set
     */
    public abstract void setByPath(String path, JsonObject value);

    /**
     * Creates a new JSON object at the given path with the specified value.
     *
     * @param path the path in the JSON structure
     * @param value the JSON object value to create
     */
    public abstract void createByPath(String path, JsonObject value);

    /**
     * Deletes the JSON object at the given path.
     *
     * @param path the path in the JSON structure
     */
    public abstract void deleteByPath(String path);
}
