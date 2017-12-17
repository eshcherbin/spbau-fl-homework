package ru.spbau.eshcherbin.fl.hw9.ast;

public class AstWhileStatement extends AstStatement {
  private AstExpression condition;
  private AstStatement body;

  public AstWhileStatement(AstExpression condition, AstStatement body, int line, int column) {
    super(line, column);
    this.condition = condition;
    this.body = body;
  }

  public AstExpression getCondition() {
    return condition;
  }

  public AstStatement getBody() {
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
    return getBody() != null ? getBody().equals(that.getBody()) : that.getBody() == null;
  }

  @Override
  public int hashCode() {
    int result = getCondition().hashCode();
    result = 31 * result + (getBody() != null ? getBody().hashCode() : 0);
    return result;
  }
}
