package ru.spbau.eshcherbin.fl.hw7.ast;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import ru.spbau.eshcherbin.fl.hw7.LParser;
import ru.spbau.eshcherbin.fl.hw7.LVisitor;

public class ExpressionVisitor implements LVisitor<AstExpression> {
  @Override
  public AstExpression visitExpressionInParentheses(LParser.ExpressionInParenthesesContext ctx) {
    return AstNodes.fromContext(ctx.expression());
  }

  @Override
  public AstExpression visitVariableAccessExpression(LParser.VariableAccessExpressionContext ctx) {
    return new AstVariableAccessExpression(
        ctx.IDENTIFIER().getText(),
        ctx.start.getLine(),
        ctx.start.getCharPositionInLine()
    );
  }

  @Override
  public AstExpression visitDecimalIntegerLiteralExpression(LParser.DecimalIntegerLiteralExpressionContext ctx) {
    return new AstDecimalIntegerLiteralExpression(
        ctx.DECIMAL_INTEGER_LITERAL().getText(),
        ctx.start.getLine(),
        ctx.start.getCharPositionInLine()
    );
  }

  @Override
  public AstExpression visitDecimalFloatingPointLiteralExpression(
      LParser.DecimalFloatingPointLiteralExpressionContext ctx) {
    return new AstDecimalFloatingPointLiteralExpression(
        ctx.DECIMAL_FLOATING_POINT_LITERAL().getText(),
        ctx.start.getLine(),
        ctx.start.getCharPositionInLine()
    );
  }

  @Override
  public AstExpression visitBinaryOperationExpression(LParser.BinaryOperationExpressionContext ctx) {
    return new AstBinaryOperationExpression(
        AstNodes.fromContext(ctx.firstOperand),
        AstNodes.fromContext(ctx.secondOperand),
        ctx.operator.getText(),
        ctx.start.getLine(),
        ctx.start.getCharPositionInLine()
    );
  }

  @Override
  public AstExpression visitDelimitedStatements(LParser.DelimitedStatementsContext ctx) {
    throw new IllegalStateException();
  }

  @Override
  public AstExpression visitProgram(LParser.ProgramContext ctx) {
    throw new IllegalStateException();
  }

  @Override
  public AstExpression visitFunctionDefinition(LParser.FunctionDefinitionContext ctx) {
    throw new IllegalStateException();
  }

  @Override
  public AstExpression visitAssignmentStatement(LParser.AssignmentStatementContext ctx) {
    throw new IllegalStateException();
  }

  @Override
  public AstExpression visitWriteStatement(LParser.WriteStatementContext ctx) {
    throw new IllegalStateException();
  }

  @Override
  public AstExpression visitReadStatement(LParser.ReadStatementContext ctx) {
    throw new IllegalStateException();
  }

  @Override
  public AstExpression visitIfStatement(LParser.IfStatementContext ctx) {
    throw new IllegalStateException();
  }

  @Override
  public AstExpression visitWhileStatement(LParser.WhileStatementContext ctx) {
    throw new IllegalStateException();
  }

  @Override
  public AstExpression visitFunctionCallStatement(LParser.FunctionCallStatementContext ctx) {
    throw new IllegalStateException();
  }

  @Override
  public AstExpression visit(ParseTree tree) {
    throw new IllegalStateException();
  }

  @Override
  public AstExpression visitChildren(RuleNode node) {
    throw new IllegalStateException();
  }

  @Override
  public AstExpression visitTerminal(TerminalNode node) {
    throw new IllegalStateException();
  }

  @Override
  public AstExpression visitErrorNode(ErrorNode node) {
    throw new IllegalStateException();
  }
}
