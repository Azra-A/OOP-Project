package bg.tu_varna.sit.a1.f23621627.commands;

/**
 * Represents a command that can be executed.
 */
public interface Command {
    /**
     * Executes the command.
     *
     * @param args command arguments, may be null or empty
     */
    void execute(String args);
}
