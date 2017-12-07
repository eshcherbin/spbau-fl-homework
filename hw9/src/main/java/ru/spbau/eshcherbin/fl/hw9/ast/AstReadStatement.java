package ru.spbau.eshcherbin.fl.hw9.ast;

public class AstReadStatement extends AstStatement {
  private String variableName;

  public AstReadStatement(String variableName, int line, int column) {
    super(line, column);
    this.variableName = variableName;
  }

  public String getVariableName() {
    return variableName;
  }

  @Override
  public void accept(AstVisitor visitor) {
    visitor.visit(this);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    AstReadStatement that = (AstReadStatement) o;

    return getVariableName().equals(that.getVariableName());
  }

  @Override
  public int hashCode() {
    return getVariableName().hashCode();
  }
}
