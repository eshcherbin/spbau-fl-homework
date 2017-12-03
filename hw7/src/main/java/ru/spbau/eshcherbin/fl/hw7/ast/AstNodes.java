package ru.spbau.eshcherbin.fl.hw7.ast;

import org.antlr.v4.runtime.Token;
import ru.spbau.eshcherbin.fl.hw7.LParser;

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

  public static AstExpression fromContext(LParser.ExpressionContext context) {
    return context.accept(new ExpressionVisitor());
  }

  public static AstStatement fromContext(LParser.StatementContext context) {
    return context.accept(new StatementVisitor());
  }
}
