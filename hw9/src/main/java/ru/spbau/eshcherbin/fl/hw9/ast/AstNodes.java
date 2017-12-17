package ru.spbau.eshcherbin.fl.hw9.ast;

import org.antlr.v4.runtime.Token;
import ru.spbau.eshcherbin.fl.hw9.LParser;
import ru.spbau.eshcherbin.fl.hw9.ParsingException;

import java.util.stream.Collectors;

public class AstNodes {
  public static AstProgram fromContext(LParser.ProgramContext context) {
    return new AstProgram(
        context.functionDefinitions.stream().map(AstNodes::fromContext).collect(Collectors.toList()),
        AstNodes.fromContext(context.statement()),
        context.start.getLine(),
        context.start.getCharPositionInLine()
    );
  }

  public static AstFunctionDefinition fromContext(LParser.FunctionDefinitionContext context) {
    return new AstFunctionDefinition(
        context.functionName.getText(),
        context.argumentNames.stream().map(Token::getText).collect(Collectors.toList()),
        AstNodes.fromContext(context.functionBody),
        context.start.getLine(),
        context.start.getCharPositionInLine()
    );
  }

  public static AstFunctionCall fromContext(LParser.FunctionCallContext context) {
    return new AstFunctionCall(
        context.functionName.getText(),
        context.arguments.stream().map(AstNodes::fromContext).collect(Collectors.toList()),
        context.start.getLine(),
        context.start.getCharPositionInLine()
    );
  }

  public static AstExpression fromContext(LParser.ExpressionContext context) {
    try {
      return context.accept(new ExpressionVisitor());
    } catch (IllegalStateException e) {
      throw new ParsingException();
    }
  }

  public static AstStatement fromContext(LParser.StatementContext context) {
    try {
      return context == null ? null : context.accept(new StatementVisitor());
    } catch (IllegalStateException e) {
      throw new ParsingException();
    }
  }
}
