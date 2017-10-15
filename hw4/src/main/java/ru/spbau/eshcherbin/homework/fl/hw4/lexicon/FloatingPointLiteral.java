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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;

    FloatingPointLiteral that = (FloatingPointLiteral) o;

    return Double.compare(that.getValue(), getValue()) == 0;
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    long temp;
    temp = Double.doubleToLongBits(getValue());
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }
}
