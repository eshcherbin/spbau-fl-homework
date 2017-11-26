package ru.spbau.eshcherbin.fl.hw7.ast;

public class AstAssignmentStatement extends AstStatement {
  private String variableName;
  private AstExpression expression;

  public AstAssignmentStatement(String variableName, AstExpression expression, int line, int column) {
    super(line, column);
    this.variableName = variableName;
    this.expression = expression;
  }

  public String getVariableName() {
    return variableName;
  }

  public AstExpression getExpression() {
    return expression;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    AstAssignmentStatement that = (AstAssignmentStatement) o;

    if (!getVariableName().equals(that.getVariableName())) return false;
    return getExpression().equals(that.getExpression());
  }

  @Override
  public int hashCode() {
    int result = getVariableName().hashCode();
    result = 31 * result + getExpression().hashCode();
    return result;
  }
}
