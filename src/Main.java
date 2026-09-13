public class Main {
  public static void main(String[] args) throws Exception {
    String input = """
        let a = 10;
        print a + 2 * 3;
        """;

    Parser p = new Parser(input.getBytes());
    p.parse();

    Interpreter i = new Interpreter(p.output());
    i.run();
  }
}
