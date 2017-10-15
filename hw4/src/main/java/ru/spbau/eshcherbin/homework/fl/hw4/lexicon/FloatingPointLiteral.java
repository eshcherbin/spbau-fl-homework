package ru.spbau.eshcherbin.homework.fl.hw4.lexicon;

public class FloatingPointLiteral extends Lexeme {
  private static final String LEXEME_KIND_FLOATING_POINT_LITERAL = "FloatingPointLiteral";

  private double value;

  public FloatingPointLiteral(double value, int line, int column, int length) {
    super(line, column, length);
    this.value = value;
  }

  public double getValue() {
    return value;
  }

  @Override
  protected String getLexemeDetails() {
    return LEXEME_KIND_FLOATING_POINT_LITERAL +
        '(' +
        getValue() +
        ')';
  }
}
