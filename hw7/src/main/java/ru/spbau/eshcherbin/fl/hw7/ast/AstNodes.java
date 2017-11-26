package ru.spbau.eshcherbin.fl.hw7.ast;

import ru.spbau.eshcherbin.fl.hw7.LParser;

public class AstNodes {
  public static AstProgram fromContext(LParser.ProgramContext context) {
    return null;
  }

  public static AstFunctionDefinition fromContext(LParser.FunctionDefinitionContext context) {
    return null;
  }

  public static AstBlock fromContext(LParser.BlockContext context) {
    return null;
  }

  public static AstExpression fromContext(LParser.ExpressionContext context) {
    return context.accept(new ExpressionVisitor());
  }

  public static AstStatement fromContext(LParser.StatementContext context) {
    return context.accept(new StatementVisitor());
  }
}
