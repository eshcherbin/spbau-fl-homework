package ru.spbau.eshcherbin.fl.hw7.ast;

public class AstWriteStatement extends AstStatement {
  private AstExpression expression;

  public AstWriteStatement(AstExpression expression, int line, int column) {
    super(line, column);
    this.expression = expression;
  }

  public AstExpression getExpression() {
    return expression;
  }

  @Override
  public void accept(AstVisitor visitor) {
    visitor.visit(this);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    AstWriteStatement that = (AstWriteStatement) o;

    return getExpression().equals(that.getExpression());
  }

  @Override
  public int hashCode() {
    return getExpression().hashCode();
  }
}
