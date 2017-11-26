package ru.spbau.eshcherbin.fl.hw7.ast;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import ru.spbau.eshcherbin.fl.hw7.LParser;
import ru.spbau.eshcherbin.fl.hw7.LVisitor;

public class StatementVisitor implements LVisitor<AstStatement> {
  @Override
  public AstStatement visitProgram(LParser.ProgramContext ctx) {
    return null;
  }

  @Override
  public AstStatement visitFunctionDefinition(LParser.FunctionDefinitionContext ctx) {
    return null;
  }

  @Override
  public AstStatement visitBlock(LParser.BlockContext ctx) {
    return null;
  }

  @Override
  public AstStatement visitExpressionStatement(LParser.ExpressionStatementContext ctx) {
    return null;
  }

  @Override
  public AstStatement visitAssignmentStatement(LParser.AssignmentStatementContext ctx) {
    return null;
  }

  @Override
  public AstStatement visitReturnStatement(LParser.ReturnStatementContext ctx) {
    return null;
  }

  @Override
  public AstStatement visitWriteStatement(LParser.WriteStatementContext ctx) {
    return null;
  }

  @Override
  public AstStatement visitReadStatement(LParser.ReadStatementContext ctx) {
    return null;
  }

  @Override
  public AstStatement visitIfStatement(LParser.IfStatementContext ctx) {
    return null;
  }

  @Override
  public AstStatement visitWhileStatement(LParser.WhileStatementContext ctx) {
    return null;
  }

  @Override
  public AstStatement visitExpressionInParentheses(LParser.ExpressionInParenthesesContext ctx) {
    return null;
  }

  @Override
  public AstStatement visitVariableAccessExpression(LParser.VariableAccessExpressionContext ctx) {
    return null;
  }

  @Override
  public AstStatement visitFunctionCallExpression(LParser.FunctionCallExpressionContext ctx) {
    return null;
  }

  @Override
  public AstStatement visitDecimalIntegerLiteralExpression(LParser.DecimalIntegerLiteralExpressionContext ctx) {
    return null;
  }

  @Override
  public AstStatement visitDecimalFloatingPointLiteralExpression(LParser.DecimalFloatingPointLiteralExpressionContext ctx) {
    return null;
  }

  @Override
  public AstStatement visitBinaryOperationExpression(LParser.BinaryOperationExpressionContext ctx) {
    return null;
  }

  @Override
  public AstStatement visit(ParseTree tree) {
    return null;
  }

  @Override
  public AstStatement visitChildren(RuleNode node) {
    return null;
  }

  @Override
  public AstStatement visitTerminal(TerminalNode node) {
    return null;
  }

  @Override
  public AstStatement visitErrorNode(ErrorNode node) {
    return null;
  }
}
