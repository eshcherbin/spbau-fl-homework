package ru.spbau.eshcherbin.fl.hw7.ast;

public class AstDecimalFloatingPointLiteralExpression extends AstExpression {
  private String value;

  public AstDecimalFloatingPointLiteralExpression(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    AstDecimalFloatingPointLiteralExpression that = (AstDecimalFloatingPointLiteralExpression) o;

    return getValue().equals(that.getValue());
  }

  @Override
  public int hashCode() {
    return getValue().hashCode();
  }
}
