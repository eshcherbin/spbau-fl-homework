package ru.spbau.eshcherbin.fl.hw9.ast;

public class AstFunctionCallStatement extends AstStatement {
  private AstFunctionCall functionCall;

  public AstFunctionCallStatement(AstFunctionCall functionCall, int line, int column) {
    super(line, column);
    this.functionCall = functionCall;
  }

  public AstFunctionCall getFunctionCall() {
    return functionCall;
  }

  @Override
  public void accept(AstVisitor visitor) {
    visitor.visit(this);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    AstFunctionCallStatement that = (AstFunctionCallStatement) o;

    return getFunctionCall().equals(that.getFunctionCall());
  }

  @Override
  public int hashCode() {
    return getFunctionCall().hashCode();
  }
}