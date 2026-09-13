public class Command {
  public enum Type {
    ADD,
    SUB,
    PUSH,
    POP,
    PRINT
  }

  public Command.Type type;
  public String arg = "";

  public Command(String[] command) {
    type = Command.Type.valueOf(command[0].toUpperCase());
    if (command.length > 1) {
      arg = command[1];
    }
  }

  public String toString() {
    return type.name() + " " + arg;
  }
}
