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
}
