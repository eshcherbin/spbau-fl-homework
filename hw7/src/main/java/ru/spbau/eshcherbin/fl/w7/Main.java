package ru.spbau.eshcherbin.fl.w7;

import org.antlr.v4.runtime.*;
import ru.spbau.eshcherbin.fl.hw7.LLexer;
import ru.spbau.eshcherbin.fl.hw7.LParser;

import java.io.IOException;
import java.util.stream.Collectors;

public class Main {
  public static void main(String[] args) {
    try {
      Lexer lexer = new LLexer(CharStreams.fromFileName("example.L"));
      LParser parser = new LParser(new BufferedTokenStream(lexer));
      System.out.println(parser.program().functionDefinitions.get(1).functionName.getText());
//      System.out.println(lexer.getAllTokens().stream().map(Object::toString).collect(Collectors.joining("\n")));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
