package ru.spbau.eshcherbin.fl.hw7.ast;

import java.util.List;

public class AstProgram extends AstNode {
  private List<AstFunctionDefinition> functionDefinitions;
  private AstStatement statement;

  public AstProgram(List<AstFunctionDefinition> functionDefinitions, AstStatement statement, int line, int column) {
    super(line, column);
    this.functionDefinitions = functionDefinitions;
    this.statement = statement;
  }

  public List<AstFunctionDefinition> getFunctionDefinitions() {
    return functionDefinitions;
  }

  public AstStatement getStatement() {
    return statement;
  }

  @Override
  public void accept(AstVisitor visitor) {
    visitor.visit(this);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    AstProgram program = (AstProgram) o;

    if (!getFunctionDefinitions().equals(program.getFunctionDefinitions())) return false;
    return getStatement().equals(program.getStatement());
  }

  @Override
  public int hashCode() {
    int result = getFunctionDefinitions().hashCode();
    result = 31 * result + getStatement().hashCode();
    return result;
  }
}
