package ru.spbau.eshcherbin.fl.hw7.ast;

public class AstWhileStatement extends AstStatement {
  private AstExpression condition;
  private AstBlock body;

  public AstWhileStatement(AstExpression condition, AstBlock body, int line, int column) {
    super(line, column);
    this.condition = condition;
    this.body = body;
  }

  public AstExpression getCondition() {
    return condition;
  }

  public AstBlock getBody() {
    return body;
  }

  @Override
  public void accept(AstVisitor visitor) {
    visitor.visit(this);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    AstWhileStatement that = (AstWhileStatement) o;

    if (!getCondition().equals(that.getCondition())) return false;
    return getBody().equals(that.getBody());
  }

  @Override
  public int hashCode() {
    int result = getCondition().hashCode();
    result = 31 * result + getBody().hashCode();
    return result;
  }
}
