package ru.spbau.eshcherbin.fl.hw7.ast;

public class AstBinaryOperationExpression extends AstExpression {
  private AstExpression leftOperand;
  private AstExpression rightOperand;
  private String operator;

  public AstBinaryOperationExpression(AstExpression leftOperand,
                                      AstExpression rightOperand,
                                      String operator,
                                      int line,
                                      int column) {
    super(line, column);
    this.leftOperand = leftOperand;
    this.rightOperand = rightOperand;
    this.operator = operator;
  }

  public AstExpression getLeftOperand() {
    return leftOperand;
  }

  public AstExpression getRightOperand() {
    return rightOperand;
  }

  public String getOperator() {
    return operator;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    AstBinaryOperationExpression that = (AstBinaryOperationExpression) o;

    if (!leftOperand.equals(that.leftOperand)) return false;
    if (!rightOperand.equals(that.rightOperand)) return false;
    return operator.equals(that.operator);
  }

  @Override
  public int hashCode() {
    int result = leftOperand.hashCode();
    result = 31 * result + rightOperand.hashCode();
    result = 31 * result + operator.hashCode();
    return result;
  }
}
