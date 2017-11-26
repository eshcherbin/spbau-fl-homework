package ru.spbau.eshcherbin.fl.hw7.ast;

public class AstReturnStatement extends AstStatement {
  private AstExpression expression;

  public AstReturnStatement(AstExpression expression) {
    this.expression = expression;
  }

  public AstExpression getExpression() {
    return expression;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    AstReturnStatement that = (AstReturnStatement) o;

    return getExpression().equals(that.getExpression());
  }

  @Override
  public int hashCode() {
    return getExpression().hashCode();
  }
}
