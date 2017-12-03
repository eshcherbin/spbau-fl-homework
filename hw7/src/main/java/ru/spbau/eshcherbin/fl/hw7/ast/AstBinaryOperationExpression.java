package ru.spbau.eshcherbin.fl.hw7.ast;

public class AstBinaryOperationExpression extends AstExpression {
  private AstExpression firstOperand;
  private AstExpression secondOperand;
  private String operator;

  public AstBinaryOperationExpression(AstExpression firstOperand,
                                      AstExpression secondOperand,
                                      String operator,
                                      int line,
                                      int column) {
    super(line, column);
    this.firstOperand = firstOperand;
    this.secondOperand = secondOperand;
    this.operator = operator;
  }

  public AstExpression getFirstOperand() {
    return firstOperand;
  }

  public AstExpression getSecondOperand() {
    return secondOperand;
  }

  public String getOperator() {
    return operator;
  }

  @Override
  public void accept(AstVisitor visitor) {
    visitor.visit(this);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    AstBinaryOperationExpression that = (AstBinaryOperationExpression) o;

    if (!firstOperand.equals(that.firstOperand)) return false;
    if (!secondOperand.equals(that.secondOperand)) return false;
    return operator.equals(that.operator);
  }

  @Override
  public int hashCode() {
    int result = firstOperand.hashCode();
    result = 31 * result + secondOperand.hashCode();
    result = 31 * result + operator.hashCode();
    return result;
  }
}
