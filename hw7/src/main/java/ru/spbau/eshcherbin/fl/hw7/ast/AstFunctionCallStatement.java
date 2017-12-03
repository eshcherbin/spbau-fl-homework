package ru.spbau.eshcherbin.fl.hw7.ast;

import java.util.List;

public class AstFunctionCallStatement extends AstStatement {
  private String functionName;
  private List<String> arguments;

  public AstFunctionCallStatement(String functionName, List<String> arguments, int line, int column) {
    super(line, column);
    this.functionName = functionName;
    this.arguments = arguments;
  }

  public String getFunctionName() {
    return functionName;
  }

  public List<String> getArguments() {
    return arguments;
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

    if (!getFunctionName().equals(that.getFunctionName())) return false;
    return getArguments().equals(that.getArguments());
  }

  @Override
  public int hashCode() {
    int result = getFunctionName().hashCode();
    result = 31 * result + getArguments().hashCode();
    return result;
  }
}
