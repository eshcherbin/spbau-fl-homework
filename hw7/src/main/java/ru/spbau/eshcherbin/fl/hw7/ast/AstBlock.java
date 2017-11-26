package ru.spbau.eshcherbin.fl.hw7.ast;

import java.util.List;

public class AstBlock extends AstNode {
  private List<AstStatement> statements;

  public AstBlock(List<AstStatement> statements, int line, int column) {
    super(line, column);
    this.statements = statements;
  }

  public List<AstStatement> getStatements() {
    return statements;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    AstBlock astBlock = (AstBlock) o;

    return getStatements().equals(astBlock.getStatements());
  }

  @Override
  public int hashCode() {
    return getStatements().hashCode();
  }
}
