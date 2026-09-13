import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

public class Interpreter {
  List<String[]> commands;
  Stack<Integer> stack = new Stack<>();
  Map<String, Integer> variables = new HashMap<>();

  public Interpreter(String input) {
    final String eol = System.getProperty("line.separator");
    var output = input.split(eol);
    commands = Arrays.stream(output)
        .map(String::strip)
        .filter((s) -> s.indexOf("//") != 0 && s != "")
        .map((s) -> s.split(" "))
        .collect(Collectors.toList());
  }

  public boolean hasMoreCommands() {
    return commands.size() != 0;
  }

  public Command nextCommand() {
    return new Command(commands.remove(0));
  }

  public void run() {
    while (hasMoreCommands()) {
      var command = nextCommand();
      switch (command.type) {
        case ADD:
          var arg2 = stack.pop();
          var arg1 = stack.pop();
          stack.push(arg1 + arg2);
          break;
        case SUB:
          arg2 = stack.pop();
          arg1 = stack.pop();
          stack.push(arg1 - arg2);
          break;
        case PUSH:
          var value = variables.get(command.arg);
          if (value != null) {
            stack.push(value);
          } else {
            stack.push(Integer.parseInt(command.arg));
          }
          break;
        case POP:
          value = stack.pop();
          variables.put(command.arg, value);
          break;
        case PRINT:
          var arg = stack.pop();
          System.out.println(arg);
          break;
      }
    }
  }

}
