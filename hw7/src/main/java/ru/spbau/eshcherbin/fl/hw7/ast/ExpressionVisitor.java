package ru.spbau.eshcherbin.fl.hw7.ast;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import ru.spbau.eshcherbin.fl.hw7.LParser;
import ru.spbau.eshcherbin.fl.hw7.LVisitor;

public class ExpressionVisitor implements LVisitor<AstExpression> {
  @Override
  public AstExpression visitProgram(LParser.ProgramContext ctx) {
    return null;
  }

  @Override
  public AstExpression visitFunctionDefinition(LParser.FunctionDefinitionContext ctx) {
    return null;
  }

  @Override
  public AstExpression visitBlock(LParser.BlockContext ctx) {
    return null;
  }

  @Override
  public AstExpression visitExpressionStatement(LParser.ExpressionStatementContext ctx) {
    return null;
  }

  @Override
  public AstExpression visitAssignmentStatement(LParser.AssignmentStatementContext ctx) {
    return null;
  }

  @Override
  public AstExpression visitReturnStatement(LParser.ReturnStatementContext ctx) {
    return null;
  }

  @Override
  public AstExpression visitWriteStatement(LParser.WriteStatementContext ctx) {
    return null;
  }

  @Override
  public AstExpression visitReadStatement(LParser.ReadStatementContext ctx) {
    return null;
  }

  @Override
  public AstExpression visitIfStatement(LParser.IfStatementContext ctx) {
    return null;
  }

  @Override
  public AstExpression visitWhileStatement(LParser.WhileStatementContext ctx) {
    return null;
  }

  @Override
  public AstExpression visitExpressionInParentheses(LParser.ExpressionInParenthesesContext ctx) {
    return null;
  }

  @Override
  public AstExpression visitVariableAccessExpression(LParser.VariableAccessExpressionContext ctx) {
    return null;
  }

  @Override
  public AstExpression visitFunctionCallExpression(LParser.FunctionCallExpressionContext ctx) {
    return null;
  }

  @Override
  public AstExpression visitDecimalIntegerLiteralExpression(LParser.DecimalIntegerLiteralExpressionContext ctx) {
    return null;
  }

  @Override
  public AstExpression visitDecimalFloatingPointLiteralExpression(LParser.DecimalFloatingPointLiteralExpressionContext ctx) {
    return null;
  }

  @Override
  public AstExpression visitBinaryOperationExpression(LParser.BinaryOperationExpressionContext ctx) {
    return null;
  }

  @Override
  public AstExpression visit(ParseTree tree) {
    return null;
  }

  @Override
  public AstExpression visitChildren(RuleNode node) {
    return null;
  }

  @Override
  public AstExpression visitTerminal(TerminalNode node) {
    return null;
  }

  @Override
  public AstExpression visitErrorNode(ErrorNode node) {
    return null;
  }
}
