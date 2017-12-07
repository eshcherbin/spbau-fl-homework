package ru.spbau.eshcherbin.fl.hw9.ast;

public abstract class AstNode {
  protected int line;
  protected int column;

  public AstNode(int line, int column) {
    this.line = line;
    this.column = column;
  }

  public void accept(AstVisitor visitor) {
    visitor.visit(this);
  }

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }
}