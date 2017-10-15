package ru.spbau.eshcherbin.homework.fl.hw4.lexicon;

public class Delimiter extends Lexeme {
  private static final String LEXEME_KIND_DELIMITER = "Delimiter";

  private DelimiterType type;

  public Delimiter(DelimiterType type, int line, int column, int length) {
    super(line, column, length);
    this.type = type;
  }

  public DelimiterType getType() {
    return type;
  }

  @Override
  protected String getLexemeDetails() {
    return LEXEME_KIND_DELIMITER +
        '(' +
        getType() +
        ')';
  }

  public enum DelimiterType {
    LPAREN,
    RPAREN,
    SEMICOLON,
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;

    Delimiter delimiter = (Delimiter) o;

    return getType() == delimiter.getType();
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (getType() != null ? getType().hashCode() : 0);
    return result;
  }
}
