package ru.spbau.eshcherbin.fl.hw7.ast;

public abstract class AstNode {
  public void accept(AstVisitor visitor) {
    visitor.visit(this);
  }
}