package ru.spbau.eshcherbin.fl.hw7.ast;

import java.util.List;

public class AstFunctionDefinition extends AstNode {
  private String name;
  private List<String> arguments;
  private AstStatement body;

  public AstFunctionDefinition(String name, List<String> arguments, AstStatement body, int line, int column) {
    super(line, column);
    this.name = name;
    this.arguments = arguments;
    this.body = body;
  }

  public String getName() {
    return name;
  }

  public List<String> getArguments() {
    return arguments;
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

    AstFunctionDefinition that = (AstFunctionDefinition) o;

    if (!getName().equals(that.getName())) return false;
    if (!getArguments().equals(that.getArguments())) return false;
    return getBody().equals(that.getBody());
  }

  @Override
  public int hashCode() {
    int result = getName().hashCode();
    result = 31 * result + getArguments().hashCode();
    result = 31 * result + getBody().hashCode();
    return result;
  }
}
