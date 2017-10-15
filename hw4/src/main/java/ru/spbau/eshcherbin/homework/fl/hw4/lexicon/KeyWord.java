package ru.spbau.eshcherbin.homework.fl.hw4.lexicon;

public class KeyWord extends Lexeme {
  private static final String LEXEME_KIND_KEYWORD = "KeyWord";

  private KeyWordType type;

  public KeyWord(KeyWordType type, int line, int column, int length) {
    super(line, column, length);
    this.type = type;
  }

  public KeyWordType getType() {
    return type;
  }

  @Override
  protected String getLexemeDetails() {
    return LEXEME_KIND_KEYWORD +
        '(' +
        getType() +
        ')';
  }

  public enum KeyWordType {
    IF,
    THEN,
    ELSE,
    WHILE,
    DO,
    READ,
    WRITE,
    BEGIN,
    END,
  }
}
