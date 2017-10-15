package ru.spbau.eshcherbin.homework.fl.hw4.lexicon;

public abstract class Lexeme {
  private int line;
  private int column;
  private int length;

  public Lexeme(int line, int column, int length) {
    this.line = line;
    this.column = column;
    this.length = length;
  }

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public int getLength() {
    return length;
  }

  protected abstract String getLexemeDetails();

  @Override
  public String toString() {
    String lexemeDetails = getLexemeDetails();
    return '{' +
        lexemeDetails + (lexemeDetails != null && lexemeDetails.length() > 0 ? ", " : "") +
        getLine() + ", " +
        getColumn() + ", " +
        (getColumn() + getLength() - 1) +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Lexeme)) return false;

    Lexeme lexeme = (Lexeme) o;

    return getLine() == lexeme.getLine() &&
        getColumn() == lexeme.getColumn() &&
        getLength() == lexeme.getLength();
  }

  @Override
  public int hashCode() {
    int result = getLine();
    result = 31 * result + getColumn();
    result = 31 * result + getLength();
    return result;
  }
}
