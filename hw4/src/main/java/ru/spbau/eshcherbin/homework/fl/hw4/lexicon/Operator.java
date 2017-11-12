package ru.spbau.eshcherbin.homework.fl.hw4.lexicon;

public class Operator extends Lexeme {
  private static final String LEXEME_KIND_OPERATOR = "Operator";

  private OperatorType type;

  public Operator(OperatorType type, int line, int column, int length) {
    super(line, column, length);
    this.type = type;
  }

  public OperatorType getType() {
    return type;
  }

  @Override
  protected String getLexemeDetails() {
    return LEXEME_KIND_OPERATOR +
        '(' +
        getType() +
        ')';
  }

  public enum OperatorType {
    PLUS,
    MINUS,
    ASTERISK,
    SLASH,
    PERCENT,
    EQ,
    NEQ,
    GR,
    GEQ,
    LE,
    LEQ,
    AND,
    OR,
    ASSIGN,
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;

    Operator operator = (Operator) o;

    return getType() == operator.getType();
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (getType() != null ? getType().hashCode() : 0);
    return result;
  }
}
