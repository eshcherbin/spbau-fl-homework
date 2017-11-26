package ru.spbau.eshcherbin.fl.hw7;

import ru.spbau.eshcherbin.fl.hw7.ast.*;

import java.util.Collections;

public class AstPrinter implements AstVisitor {
  private static final String INDENTATION_UNIT = "|   ";

  private int indentationLevel = 0;

  @Override
  public void visit(AstAssignmentStatement node) {
    printlnWithIndentation("assignment {variable name: " + node.getVariableName()
        + "; line: " + node.getLine()
        + "; column: " + node.getColumn()
        + "}");
    stepIn();
    printlnWithIndentation("assigned expression:");
    stepIn();
    node.getExpression().accept(this);
    indentationLevel -= 2;
  }

  @Override
  public void visit(AstBinaryOperationExpression node) {
    printlnWithIndentation("binary operation {operator: " + node.getOperator()
        + "; line: " + node.getLine()
        + "; column: " + node.getColumn()
        + "}");
    stepIn();
    printlnWithIndentation("left operand:");
    stepIn();
    node.getLeftOperand().accept(this);
    stepOut();
    printlnWithIndentation("right operand:");
    stepIn();
    node.getRightOperand().accept(this);
    indentationLevel -= 2;
  }

  @Override
  public void visit(AstBlock node) {
    printlnWithIndentation("block start {line: " + node.getLine() + "; column: " + node.getColumn() + "}");
    stepIn();
    for (AstStatement statement : node.getStatements()) {
      statement.accept(this);
    }
    stepOut();
  }

  @Override
  public void visit(AstDecimalFloatingPointLiteralExpression node) {
    printlnWithIndentation("floating point literal {value: " + node.getValue()
        + "; line: " + node.getLine()
        + "; column: " + node.getColumn()
        + "}");
  }

  @Override
  public void visit(AstDecimalIntegerLiteralExpression node) {
    printlnWithIndentation("integer literal {value: " + node.getValue()
        + "; line: " + node.getLine()
        + "; column: " + node.getColumn()
        + "}");
  }

  @Override
  public void visit(AstExpressionStatement node) {
    printlnWithIndentation("expression statement {"
        + "line: " + node.getLine()
        + "; column: " + node.getColumn()
        + "}");
    stepIn();
    node.getExpression().accept(this);
    stepOut();
  }

  @Override
  public void visit(AstFunctionCallExpression node) {
    printlnWithIndentation("function call {name : " + node.getFunctionName()
        + (node.getArguments().isEmpty() ? "; no arguments" : "")
        + "; line: " + node.getLine()
        + "; column: " + node.getColumn()
        + "}");
    stepIn();
    int i = 0;
    for (AstExpression argument : node.getArguments()) {
      printlnWithIndentation("argument" + i + ":");
      ++i;
      stepIn();
      argument.accept(this);
      stepOut();
    }
    stepOut();
  }

  @Override
  public void visit(AstFunctionDefinition node) {
    printlnWithIndentation("function definition {name: " + node.getName()
        + (node.getArguments().isEmpty() ? "; no arguments"
                                         : "; arguments: " + String.join(", ", node.getArguments()))
        + "; line: " + node.getLine()
        + "; column: " + node.getColumn()
        + "}");
    printlnWithIndentation("body:");
    node.getBody().accept(this);
  }

  @Override
  public void visit(AstIfStatement node) {
    printlnWithIndentation("if {"
        + (node.getElseBody() == null ? "without else; " : "")
        + "line: " + node.getLine()
        + "; column: " + node.getColumn()
        + "}");
    stepIn();
    printlnWithIndentation("condition:");
    stepIn();
    node.getCondition().accept(this);
    stepOut();
    printlnWithIndentation("then body:");
    node.getThenBody().accept(this);
    if (node.getElseBody() != null) {
      printlnWithIndentation("else body:");
      node.getElseBody().accept(this);
    }
    stepOut();
  }

  @Override
  public void visit(AstProgram node) {
    indentationLevel = 0;
    for (AstFunctionDefinition functionDefinition : node.getFunctionDefinitions()) {
      functionDefinition.accept(this);
    }
  }

  @Override
  public void visit(AstReadStatement node) {
    printlnWithIndentation("read {variable name: " + node.getVariableName()
        + "; line: " + node.getLine()
        + "; column: " + node.getColumn()
        + "}");
  }

  @Override
  public void visit(AstReturnStatement node) {
    printlnWithIndentation("return {"
        + "line: " + node.getLine()
        + "; column: " + node.getColumn()
        + "}");
    stepIn();
    node.getExpression().accept(this);
    stepOut();
  }

  @Override
  public void visit(AstVariableAccessExpression node) {
    printlnWithIndentation("variable access {variable name: " + node.getVariableName()
        + "; line:" + node.getLine()
        + "; column:" + node.getColumn()
        + "}");
  }

  @Override
  public void visit(AstWhileStatement node) {
    printlnWithIndentation("while {"
        + "line: " + node.getLine()
        + "; column: " + node.getColumn()
        + "}");
    stepIn();
    printlnWithIndentation("condition:");
    stepIn();
    node.getCondition().accept(this);
    stepOut();
    printlnWithIndentation("body:");
    node.getBody().accept(this);
    stepOut();
  }

  @Override
  public void visit(AstWriteStatement node) {
    printlnWithIndentation("write {"
        + "line: " + node.getLine()
        + "; column: " + node.getColumn()
        + "}");
    stepIn();
    node.getExpression().accept(this);
    stepOut();
  }

  @Override
  public void visit(AstNode node) {
    throw new IllegalStateException();
  }

  @Override
  public void visit(AstStatement node) {
    throw new IllegalStateException();
  }

  @Override
  public void visit(AstExpression node) {
    throw new IllegalStateException();
  }

  private void stepIn() {
    ++indentationLevel;
  }

  private void stepOut() {
    --indentationLevel;
  }

  private void printlnWithIndentation(String string) {
    System.out.print(String.join("", Collections.nCopies(indentationLevel, INDENTATION_UNIT)));
    System.out.println(string);
  }
}
