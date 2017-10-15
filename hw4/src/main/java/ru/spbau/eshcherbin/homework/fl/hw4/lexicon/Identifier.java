package ru.spbau.eshcherbin.homework.fl.hw4.lexicon;

public class Identifier extends Lexeme {
  private static final String LEXEME_KIND_IDENTIFIER = "Identifier";

  private String name;

  public Identifier(String name, int line, int column, int length) {
    super(line, column, length);
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  protected String getLexemeDetails() {
    return LEXEME_KIND_IDENTIFIER +
            "(\"" +
            getName() +
            "\")";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;

    Identifier that = (Identifier) o;

    return getName() != null ? getName().equals(that.getName()) : that.getName() == null;
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (getName() != null ? getName().hashCode() : 0);
    return result;
  }
}
