package ru.spbau.eshcherbin.fl.hw9.ast;

import java.util.List;

public class AstDelimitedStatements extends AstStatement {
  private List<AstStatement> statements;

  public AstDelimitedStatements(List<AstStatement> statements, int line, int column) {
    super(line, column);
    this.statements = statements;
  }

  public List<AstStatement> getStatements() {
    return statements;
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

    return getStatements().equals(that.getStatements());
  }

  @Override
  public int hashCode() {
    return getStatements().hashCode();
  }
}
