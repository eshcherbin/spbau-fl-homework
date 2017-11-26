package ru.spbau.eshcherbin.fl.hw7.ast;

public abstract class AstExpression extends AstNode {
  public AstExpression(int line, int column) {
    super(line, column);
  }

  @Override
  public void accept(AstVisitor visitor) {
    visitor.visit(this);
  }
}
