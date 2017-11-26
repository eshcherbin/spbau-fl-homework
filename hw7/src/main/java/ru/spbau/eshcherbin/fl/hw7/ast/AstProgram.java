package ru.spbau.eshcherbin.fl.hw7.ast;

import java.util.List;

public class AstProgram extends AstNode {
  private List<AstFunctionDefinition> functionDefinitions;

  public AstProgram(List<AstFunctionDefinition> functionDefinitions, int line, int column) {
    super(line, column);
    this.functionDefinitions = functionDefinitions;
  }

  public List<AstFunctionDefinition> getFunctionDefinitions() {
    return functionDefinitions;
  }

  @Override
  public void accept(AstVisitor visitor) {
    visitor.visit(this);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    AstProgram that = (AstProgram) o;

    return getFunctionDefinitions().equals(that.getFunctionDefinitions());
  }

  @Override
  public int hashCode() {
    return getFunctionDefinitions().hashCode();
  }
}
