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
    printlnWithIndentation("assigned expression:");
    stepIn();
    node.getExpression().accept(this);
    stepOut();
  }

  @Override
  public void visit(AstBinaryOperationExpression node) {
    printlnWithIndentation("binary operation {operator: " + node.getOperator()
        + "; line: " + node.getLine()
        + "; column: " + node.getColumn()
        + "}");
    printlnWithIndentation("first operand:");
    stepIn();
    node.getFirstOperand().accept(this);
    stepOut();
    printlnWithIndentation("second operand:");
    stepIn();
    node.getSecondOperand().accept(this);
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
  public void visit(AstFunctionCallStatement node) {
    printlnWithIndentation("function call {name : " + node.getFunctionName()
        + (node.getArguments().isEmpty() ? "; no arguments"
                                         : "; arguments: " + String.join(", ", node.getArguments()))
        + "; line: " + node.getLine()
        + "; column: " + node.getColumn()
        + "}");
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
    stepIn();
    node.getBody().accept(this);
    stepOut();
  }

  @Override
  public void visit(AstIfStatement node) {
    printlnWithIndentation("if {"
        + (node.getElseBody() == null ? "without else; " : "")
        + "line: " + node.getLine()
        + "; column: " + node.getColumn()
        + "}");
    printlnWithIndentation("condition:");
    stepIn();
    node.getCondition().accept(this);
    stepOut();
    printlnWithIndentation("then body:");
    stepIn();
    node.getThenBody().accept(this);
    stepOut();
    if (node.getElseBody() != null) {
      printlnWithIndentation("else body:");
      stepIn();
      node.getElseBody().accept(this);
      stepOut();
    }
  }

  @Override
  public void visit(AstProgram node) {
    resetIndentation();
    printlnWithIndentation("program:");
    for (AstFunctionDefinition functionDefinition : node.getFunctionDefinitions()) {
      functionDefinition.accept(this);
    }
    printlnWithIndentation("statement:");
    stepIn();
    node.getStatement().accept(this);
    stepOut();
  }

  @Override
  public void visit(AstReadStatement node) {
    printlnWithIndentation("read {variable name: " + node.getVariableName()
        + "; line: " + node.getLine()
        + "; column: " + node.getColumn()
        + "}");
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
    printlnWithIndentation("condition:");
    stepIn();
    node.getCondition().accept(this);
    stepOut();
    printlnWithIndentation("body:");
    stepIn();
    node.getBody().accept(this);
    stepOut();
  }

  @Override
  public void visit(AstWriteStatement node) {
    printlnWithIndentation("write {"
        + "line: " + node.getLine()
        + "; column: " + node.getColumn()
        + "}");
    printlnWithIndentation("expression:");
    stepIn();
    node.getExpression().accept(this);
    stepOut();
  }

  @Override
  public void visit(AstDelimitedStatements node) {
    printlnWithIndentation("delimited statements {"
        + "line: " + node.getLine()
        + "; column: " + node.getColumn()
        + "}");
    printlnWithIndentation("first statement:");
    stepIn();
    node.getFirstStatement().accept(this);
    stepOut();
    printlnWithIndentation("second statement:");
    stepIn();
    node.getSecondStatement().accept(this);
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

  private void resetIndentation() {
    indentationLevel = 0;
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
