package ru.spbau.eshcherbin.fl.hw7.ast;

public class AstIfStatement {
  private AstExpression condition;
  private AstBlock thenBody;
  private AstBlock elseBody;

  public AstIfStatement(AstExpression condition, AstBlock thenBody) {
    this.condition = condition;
    this.thenBody = thenBody;
    this.elseBody = null;
  }

  public AstIfStatement(AstExpression condition, AstBlock thenBody, AstBlock elseBody) {
    this.condition = condition;
    this.thenBody = thenBody;
    this.elseBody = elseBody;
  }

  public AstExpression getCondition() {
    return condition;
  }

  public AstBlock getThenBody() {
    return thenBody;
  }

  public AstBlock getElseBody() {
    return elseBody;
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
