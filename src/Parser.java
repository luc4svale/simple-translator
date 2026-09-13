public class Parser {
  private Scanner scan;
  private Token currentToken;
  private StringBuilder buffer = new StringBuilder();

  public Parser(byte[] input) {
    scan = new Scanner(input);
    currentToken = scan.nextToken();
  }

  private void nextToken() {
    currentToken = scan.nextToken();
  }

  private void match(TokenType t) {
    if (currentToken.type == t) {
      nextToken();
    } else {
      throw new Error("sintax error");
    }
  }

  private void emit(String s) {
    System.out.println(s);
    buffer.append(s).append(System.getProperty("line.separator"));
  }

  public String output() {
    return buffer.toString();
  }

  void expr() {
    term();
    oper();
  }

  void term() {
    if (currentToken.type == TokenType.NUMBER)
      number();
    else if (currentToken.type == TokenType.IDENT) {
      emit("push " + currentToken.lexeme);
      match(TokenType.IDENT);
    } else {
      throw new Error("syntax error");
    }
  }

  void number() {
    emit("push " + currentToken.lexeme);
    match(TokenType.NUMBER);
  }

  void oper() {
    if (currentToken.type == TokenType.PLUS) {
      match(TokenType.PLUS);
      term();
      emit("add");
      oper();
    } else if (currentToken.type == TokenType.MINUS) {
      match(TokenType.MINUS);
      term();
      emit("sub");
      oper();
    }
  }

  void letStatement() {
    match(TokenType.LET);
    var id = currentToken.lexeme;
    match(TokenType.IDENT);
    match(TokenType.EQ);
    expr();
    emit("pop " + id);
    match(TokenType.SEMICOLON);
  }

  void printStatement() {
    match(TokenType.PRINT);
    expr();
    emit("print");
    match(TokenType.SEMICOLON);
  }

  void statement() {
    if (currentToken.type == TokenType.PRINT) {
      printStatement();
    } else if (currentToken.type == TokenType.LET) {
      letStatement();
    } else {
      throw new Error("sintax error");
    }
  }

  void statements() {
    while (currentToken.type != TokenType.EOF) {
      statement();
    }
  }

  public void parse() {
    statements();
  }

}