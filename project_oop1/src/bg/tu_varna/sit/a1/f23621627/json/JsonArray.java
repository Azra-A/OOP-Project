package bg.tu_varna.sit.a1.f23621627.json;

import java.util.*;

/**
 * Represents a JSON array, which is an ordered collection of JsonObjects.
 */
public class JsonArray extends JsonObject {
    private final List<JsonObject> elements = new ArrayList<>();

    /**
     * Adds a JsonObject to this array.
     *
     * @param obj the JsonObject to add
     */
    public void add(JsonObject obj) {
        elements.add(obj);
    }

    /**
     * Returns the JSON string representation of this array with indentation.
     *
     * @param indent the current indentation level
     * @return the JSON string of the array
     */
    @Override
    public String toJsonString(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        for (JsonObject obj : elements) {
            sb.append(" ".repeat(indent + 2)).append(obj.toJsonString(indent + 2)).append(",\n");
        }
        sb.append(" ".repeat(indent)).append("]");
        return sb.toString();
    }

    /**
     * Returns null since this method is not implemented for JsonArray.
     *
     * @param path the JSON path (ignored)
     * @return null always
     */
    @Override
    public JsonObject getByPath(String path) {
        return null;
    }

    /**
     * Does nothing since this method is not implemented for JsonArray.
     *
     * @param path the JSON path (ignored)
     * @param value the JsonObject to set (ignored)
     */
    @Override
    public void setByPath(String path, JsonObject value) {}

    /**
     * Does nothing since this method is not implemented for JsonArray.
     *
     * @param path the JSON path (ignored)
     * @param value the JsonObject to create (ignored)
     */
    @Override
    public void createByPath(String path, JsonObject value) {}

    /**
     * Does nothing since this method is not implemented for JsonArray.
     *
     * @param path the JSON path (ignored)
     */
    @Override
    public void deleteByPath(String path) {}

    /**
     * Returns the list of elements in this JSON array.
     *
     * @return the list of JsonObjects contained in the array
     */
    public List<JsonObject> getElements() {
        return elements;
    }
}
