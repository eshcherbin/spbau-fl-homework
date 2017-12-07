package ru.spbau.eshcherbin.fl.hw9.ast;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import ru.spbau.eshcherbin.fl.hw9.LParser;
import ru.spbau.eshcherbin.fl.hw9.LVisitor;

import java.util.stream.Collectors;

public class StatementVisitor implements LVisitor<AstStatement> {
  @Override
  public AstStatement visitAssignmentStatement(LParser.AssignmentStatementContext ctx) {
    return new AstAssignmentStatement(
        ctx.IDENTIFIER().getText(),
        AstNodes.fromContext(ctx.expression()),
        ctx.start.getLine(),
        ctx.start.getCharPositionInLine()
    );
  }

  @Override
  public AstStatement visitWriteStatement(LParser.WriteStatementContext ctx) {
    return new AstWriteStatement(
        AstNodes.fromContext(ctx.expression()),
        ctx.start.getLine(),
        ctx.start.getCharPositionInLine()
    );
  }

  @Override
  public AstStatement visitDelimitedStatements(LParser.DelimitedStatementsContext ctx) {
    return new AstDelimitedStatements(
        ctx.statements.stream().map(AstNodes::fromContext).collect(Collectors.toList()),
        ctx.start.getLine(),
        ctx.start.getCharPositionInLine()
    );
  }

  @Override
  public AstStatement visitReadStatement(LParser.ReadStatementContext ctx) {
    return new AstReadStatement(
        ctx.IDENTIFIER().getText(),
        ctx.start.getLine(),
        ctx.start.getCharPositionInLine()
    );
  }

  @Override
  public AstStatement visitIfStatement(LParser.IfStatementContext ctx) {
    if (ctx.elseBody == null) {
      return new AstIfStatement(
          AstNodes.fromContext(ctx.condition),
          AstNodes.fromContext(ctx.thenBody),
          ctx.start.getLine(),
          ctx.start.getCharPositionInLine()
      );
    } else {
      return new AstIfStatement(
          AstNodes.fromContext(ctx.condition),
          AstNodes.fromContext(ctx.thenBody),
          AstNodes.fromContext(ctx.elseBody),
          ctx.start.getLine(),
          ctx.start.getCharPositionInLine()
      );
    }
  }

  @Override
  public AstStatement visitWhileStatement(LParser.WhileStatementContext ctx) {
    return new AstWhileStatement(
        AstNodes.fromContext(ctx.condition),
        AstNodes.fromContext(ctx.body),
        ctx.start.getLine(),
        ctx.start.getCharPositionInLine()
    );
  }

  @Override
  public AstStatement visitFunctionCallStatement(LParser.FunctionCallStatementContext ctx) {
    return new AstFunctionCallStatement(
        AstNodes.fromContext(ctx.functionCall()),
        ctx.start.getLine(),
        ctx.start.getCharPositionInLine()
    );
  }

  @Override
  public AstStatement visitProgram(LParser.ProgramContext ctx) {
    throw new IllegalStateException();
  }

  @Override
  public AstStatement visitFunctionDefinition(LParser.FunctionDefinitionContext ctx) {
    throw new IllegalStateException();
  }

  @Override
  public AstStatement visitFunctionCall(LParser.FunctionCallContext ctx) {
    throw new IllegalStateException();
  }

  @Override
  public AstStatement visitExpressionInParentheses(LParser.ExpressionInParenthesesContext ctx) {
    throw new IllegalStateException();
  }

  @Override
  public AstStatement visitVariableAccessExpression(LParser.VariableAccessExpressionContext ctx) {
    throw new IllegalStateException();
  }

  @Override
  public AstStatement visitDecimalIntegerLiteralExpression(LParser.DecimalIntegerLiteralExpressionContext ctx) {
    throw new IllegalStateException();
  }

  @Override
  public AstStatement visitDecimalFloatingPointLiteralExpression(
      LParser.DecimalFloatingPointLiteralExpressionContext ctx) {
    throw new IllegalStateException();
  }

  @Override
  public AstStatement visitFunctionCallExpression(LParser.FunctionCallExpressionContext ctx) {
    throw new IllegalStateException();
  }

  @Override
  public AstStatement visitBinaryOperationExpression(LParser.BinaryOperationExpressionContext ctx) {
    throw new IllegalStateException();
  }

  @Override
  public AstStatement visit(ParseTree tree) {
    throw new IllegalStateException();
  }

  @Override
  public AstStatement visitChildren(RuleNode node) {
    throw new IllegalStateException();
  }

  @Override
  public AstStatement visitTerminal(TerminalNode node) {
    throw new IllegalStateException();
  }

  @Override
  public AstStatement visitErrorNode(ErrorNode node) {
    throw new IllegalStateException();
  }
}
