package ru.spbau.eshcherbin.fl.hw9;

import ru.spbau.eshcherbin.fl.hw9.ast.*;

import java.util.Collections;

public class AstPrinter implements AstVisitor {
  protected static final String INDENTATION_UNIT = "|   ";

  protected int indentationLevel = 0;

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
  public void visit(AstFunctionCall node) {
    printlnWithIndentation("function call {name : " + node.getFunctionName()
        + (node.getArguments().isEmpty() ? "; no arguments" : "")
        + "; line: " + node.getLine()
        + "; column: " + node.getColumn()
        + "}");
    int i = 0;
    for (AstExpression argument : node.getArguments()) {
      printlnWithIndentation("argument" + i + ":");
      ++i;
      stepIn();
      argument.accept(this);
      stepOut();
    }
  }

  @Override
  public void visit(AstFunctionCallStatement node) {
    node.getFunctionCall().accept(this);
  }

  @Override
  public void visit(AstFunctionCallExpression node) {
    node.getFunctionCall().accept(this);
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
    if (node.getBody() == null) {
      printlnWithIndentation("empty body");
    } else {
      node.getBody().accept(this);
    }
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
    if (node.getThenBody() == null) {
      printlnWithIndentation("empty body");
    } else {
      node.getThenBody().accept(this);
    }
    stepOut();
    if (node.getElseBody() != null) {
      printlnWithIndentation("else body:");
      stepIn();
      if (node.getThenBody() == null) {
        printlnWithIndentation("empty body");
      } else {
        node.getElseBody().accept(this);
      }
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
    if (node.getStatement() == null) {
      printlnWithIndentation("empty statement");
    } else {
      node.getStatement().accept(this);
    }
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
    if (node.getBody() == null) {
      printlnWithIndentation("empty body");
    } else {
      printlnWithIndentation("body:");
      stepIn();
      node.getBody().accept(this);
      stepOut();
    }
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
    int i = 0;
    for (AstStatement statement : node.getStatements()) {
      printlnWithIndentation("statement" + i + ":");
      ++i;
      stepIn();
      statement.accept(this);
      stepOut();
    }
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

  protected void resetIndentation() {
    indentationLevel = 0;
  }

  protected void stepIn() {
    ++indentationLevel;
  }

  protected void stepOut() {
    --indentationLevel;
  }

  protected void printlnWithIndentation(String string) {
    System.out.print(String.join("", Collections.nCopies(indentationLevel, INDENTATION_UNIT)));
    System.out.println(string);
  }
}
