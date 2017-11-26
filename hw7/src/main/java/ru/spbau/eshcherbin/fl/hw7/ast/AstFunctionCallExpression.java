package ru.spbau.eshcherbin.fl.hw7.ast;

import java.util.List;

public class AstFunctionCallExpression extends AstExpression {
  private String functionName;
  private List<AstExpression> arguments;

  public AstFunctionCallExpression(String functionName, List<AstExpression> arguments, int line, int column) {
    super(line, column);
    this.functionName = functionName;
    this.arguments = arguments;
  }

  public String getFunctionName() {
    return functionName;
  }

  public List<AstExpression> getArguments() {
    return arguments;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    AstFunctionCallExpression that = (AstFunctionCallExpression) o;

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
