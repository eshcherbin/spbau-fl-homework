package ru.spbau.eshcherbin.fl.hw9.ast;

public class AstIfStatement extends AstStatement {
  private AstExpression condition;
  private AstStatement thenBody;
  private AstStatement elseBody;

  public AstIfStatement(AstExpression condition, AstStatement thenBody, int line, int column) {
    super(line, column);
    this.condition = condition;
    this.thenBody = thenBody;
    this.elseBody = null;
  }

  public AstIfStatement(AstExpression condition, AstStatement thenBody, AstStatement elseBody, int line, int column) {
    super(line, column);
    this.condition = condition;
    this.thenBody = thenBody;
    this.elseBody = elseBody;
  }

  public AstExpression getCondition() {
    return condition;
  }

  public AstStatement getThenBody() {
    return thenBody;
  }

  public AstStatement getElseBody() {
    return elseBody;
  }

  @Override
  public void accept(AstVisitor visitor) {
    visitor.visit(this);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    AstIfStatement that = (AstIfStatement) o;

    if (!getCondition().equals(that.getCondition())) return false;
    if (!getThenBody().equals(that.getThenBody())) return false;
    return getElseBody() != null ? getElseBody().equals(that.getElseBody()) : that.getElseBody() == null;
  }

  @Override
  public int hashCode() {
    int result = getCondition().hashCode();
    result = 31 * result + getThenBody().hashCode();
    result = 31 * result + (getElseBody() != null ? getElseBody().hashCode() : 0);
    return result;
  }
}
