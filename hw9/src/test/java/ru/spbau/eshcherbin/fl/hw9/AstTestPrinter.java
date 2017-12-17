package ru.spbau.eshcherbin.fl.hw9;

import java.util.Collections;

public class AstTestPrinter extends AstPrinter {
  private StringBuilder stringBuilder = new StringBuilder();

  public String getString() {
    return stringBuilder.toString();
  }

  @Override
  protected void printlnWithIndentation(String string) {
    String indentation = String.join("", Collections.nCopies(indentationLevel, INDENTATION_UNIT));
    System.out.print(indentation);
    System.out.println(string);
    stringBuilder.append(indentation).append(string).append('\n');
  }
}
