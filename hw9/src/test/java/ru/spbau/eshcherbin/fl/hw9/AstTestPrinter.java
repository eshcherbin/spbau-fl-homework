package ru.spbau.eshcherbin.fl.hw9;

import java.util.Collections;

public class AstTestPrinter extends AstPrinter {
  private StringBuilder stringBuilder = new StringBuilder();

  public String getString() {
    return stringBuilder.toString();
  }

  @Override
  protected void printlnWithIndentation(String string) {
    stringBuilder
        .append(String.join("", Collections.nCopies(indentationLevel, INDENTATION_UNIT)))
        .append(string)
        .append('\n');
  }
}
