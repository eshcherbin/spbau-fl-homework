package ru.spbau.eshcherbin.fl.hw7.ast;

public class AstExpressionStatement extends AstStatement {
  private AstExpression expression;

  public AstExpressionStatement(AstExpression expression, int line, int column) {
    super(line, column);
    this.expression = expression;
  }

  public AstExpression getExpression() {
    return expression;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    AstExpressionStatement that = (AstExpressionStatement) o;

    return getExpression().equals(that.getExpression());
  }

  @Override
  public int hashCode() {
    return getExpression().hashCode();
  }
}
