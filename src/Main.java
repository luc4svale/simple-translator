public class Main {
  public static void main(String[] args) throws Exception {
    String input = """
        let a = 42 + 2;
        let b = 15 + 3;
        print a + b;
        """;
    Parser p = new Parser(input.getBytes());
    p.parse();

    Interpreter i = new Interpreter(p.output());
    i.run();
  }
}
