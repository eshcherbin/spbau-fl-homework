package ru.spbau.eshcherbin.fl.hw7.ast;

public class AstDelimitedStatements extends AstStatement {
  private AstStatement firstStatement;
  private AstStatement secondStatement;

  public AstDelimitedStatements(AstStatement firstStatement, AstStatement secondStatement, int line, int column) {
    super(line, column);
    this.firstStatement = firstStatement;
    this.secondStatement = secondStatement;
  }

  public AstStatement getFirstStatement() {
    return firstStatement;
  }

  public AstStatement getSecondStatement() {
    return secondStatement;
  }

  @Override
  public void accept(AstVisitor visitor) {
    visitor.visit(this);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    AstDelimitedStatements that = (AstDelimitedStatements) o;

    if (!getFirstStatement().equals(that.getFirstStatement())) return false;
    return getSecondStatement().equals(that.getSecondStatement());
  }

  @Override
  public int hashCode() {
    int result = getFirstStatement().hashCode();
    result = 31 * result + getSecondStatement().hashCode();
    return result;
  }
}
