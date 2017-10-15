package ru.spbau.eshcherbin.homework.fl.hw4.lexicon;

public class BooleanLiteral extends Lexeme {
  private static final String LEXEME_KIND_BOOLEAN_LITERAL = "BooleanLiteral";

  private boolean value;

  public BooleanLiteral(boolean value, int line, int column, int length) {
    super(line, column, length);
    this.value = value;
  }

  public boolean getValue() {
    return value;
  }

  @Override
  protected String getLexemeDetails() {
    return LEXEME_KIND_BOOLEAN_LITERAL +
        '(' +
        getValue() +
        ')';
  }
}
