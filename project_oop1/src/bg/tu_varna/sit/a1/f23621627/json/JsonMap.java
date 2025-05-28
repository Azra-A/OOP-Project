package bg.tu_varna.sit.a1.f23621627.json;

import java.util.*;

/**
 * Represents a JSON object, storing key-value pairs with string keys and JsonObject values.
 */
public class JsonMap extends JsonObject {
    private final Map<String, JsonObject> members = new LinkedHashMap<>();

    /**
     * Adds or updates a member key-value pair in this JSON object.
     *
     * @param key the key of the member
     * @param value the JsonObject value to associate with the key
     */
    public void put(String key, JsonObject value) {
        members.put(key, value);
    }

    /**
     * Returns the JSON string representation of this object with indentation.
     *
     * @param indent the current indentation level
     * @return the JSON string of the object
     */
    @Override
    public String toJsonString(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");

        int count = 0;
        int size = members.size();

        // Map.Entry - gives key and value in one place
        for (Map.Entry<String, JsonObject> entry : members.entrySet()) {
            sb.append(" ".repeat(indent + 2))
                    .append("\"").append(entry.getKey()).append("\": ")
                    .append(entry.getValue().toJsonString(indent + 2));

            count++;
            if (count < size) {
                sb.append(",");
            }
            sb.append("\n");
        }

        sb.append(" ".repeat(indent)).append("}");
        return sb.toString();
    }

    /**
     * Retrieves a JsonObject from this map by a dot-separated path.
     *
     * @param path dot-separated keys to navigate nested objects
     * @return the JsonObject at the specified path, or null if not found
     */
    @Override
    public JsonObject getByPath(String path) {
        String[] parts = path.split("\\.");
        JsonObject current = this;

        for (String part : parts) {
            if (!(current instanceof JsonMap))
                return null;
            current = ((JsonMap) current).members.get(part);
            if (current == null)
                return null;
        }
        return current;
    }

    /**
     * Sets the value at the specified dot-separated path if the key exists.
     *
     * @param path dot-separated keys to navigate nested objects
     * @param value the JsonObject to set at the path
     */
    @Override
    public void setByPath(String path, JsonObject value) {
        String[] parts = path.split("\\.");
        JsonMap current = this;

        for (int i = 0; i < parts.length - 1; i++) {
            JsonObject next = current.members.get(parts[i]);

            if (!(next instanceof JsonMap)) {
                System.out.println("Path is invalid or not a JsonMap: " + parts[i]);
            }

            current = (JsonMap) next;
        }

        String lastKey = parts[parts.length - 1];
        if (!current.members.containsKey(lastKey)) {
            System.out.println("Cannot set: key '" + lastKey + "' not found.");
        }

        current.members.put(lastKey, value);
    }

    /**
     * Creates a new JsonObject at the specified dot-separated path if it does not already exist.
     *
     * @param path dot-separated keys to navigate or create nested objects
     * @param value the JsonObject to create at the path
     */
    @Override
    public void createByPath(String path, JsonObject value) {
        String[] parts = path.split("\\.");
        JsonMap current = this;

        for (int i = 0; i < parts.length - 1; i++) {
            JsonObject next = current.members.get(parts[i]);
            if (next == null) {
                JsonMap newMap = new JsonMap();
                current.members.put(parts[i], newMap);
                current = newMap;
            } else if (next instanceof JsonMap) {
                current = (JsonMap) next;
            } else {
                System.out.println("Cannot create inside non-object at: " + parts[i]);
            }
        }

        String lastKey = parts[parts.length - 1];
        if (current.members.containsKey(lastKey)) {
            System.out.println("Key already exists.");
            return;
        } else {
            current.members.put(lastKey, value);
            System.out.println("Successfully created value at path: " + path);
        }
    }

    /**
     * Deletes the JsonObject at the specified dot-separated path.
     *
     * @param path dot-separated keys to navigate nested objects
     */
    @Override
    public void deleteByPath(String path) {
        String[] parts = path.split("\\.");
        JsonMap current = this;

        for (int i = 0; i < parts.length - 1; i++) {
            JsonObject next = current.members.get(parts[i]);
            if (!(next instanceof JsonMap)) {
                System.out.println("Path is invalid: " + parts[i]);
            }
            current = (JsonMap) next;
        }

        String lastKey = parts[parts.length - 1];
        if (!current.members.containsKey(lastKey)) {
            System.out.println("Key not found: " + lastKey);
        } else {
            current.members.remove(lastKey);
            System.out.println("Successfully deleted element at path: " + path);

        }
    }

    /**
     * Returns the map of members contained in this JSON object.
     *
     * @return the map of key-value pairs in this JSON object
     */
    public Map<String, JsonObject> getMembers() {
        return members;
    }
}
