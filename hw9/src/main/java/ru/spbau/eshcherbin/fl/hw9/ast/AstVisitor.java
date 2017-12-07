package ru.spbau.eshcherbin.fl.hw9.ast;

public interface AstVisitor {
  void visit(AstAssignmentStatement node);

  void visit(AstBinaryOperationExpression node);

  void visit(AstDecimalFloatingPointLiteralExpression node);

  void visit(AstDecimalIntegerLiteralExpression node);

  void visit(AstFunctionCall node);

  void visit(AstFunctionCallStatement node);

  void visit(AstFunctionCallExpression node);

  void visit(AstFunctionDefinition node);

  void visit(AstIfStatement node);

  void visit(AstNode node);

  void visit(AstStatement node);

  void visit(AstExpression node);

  void visit(AstProgram node);

  void visit(AstReadStatement node);

  void visit(AstVariableAccessExpression node);

  void visit(AstWhileStatement node);

  void visit(AstWriteStatement node);

  void visit(AstDelimitedStatements node);
}
