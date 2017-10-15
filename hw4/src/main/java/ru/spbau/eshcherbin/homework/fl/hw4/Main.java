package ru.spbau.eshcherbin.homework.fl.hw4;

import ru.spbau.eshcherbin.homework.fl.hw4.lexicon.Lexeme;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
  public static void main(String[] args) {
    if (args.length != 1) {
      System.err.println("One command line argument expected, but " + args.length + " found");
      System.exit(1);
    }

    String source = "";
    try {
      source = new String(Files.readAllBytes(Paths.get(args[0])));
    } catch (IOException e) {
      System.err.println("Error while attempting to read the given file: " + e);
      System.exit(2);
    }

    LLexer lexer = new LLexer(null);
    lexer.reset(source, 0, source.length(), LLexer.YYINITIAL);

    try {
      for (Lexeme lexeme = lexer.yylex(); lexeme != null; lexeme = lexer.yylex()) {
        System.out.println(lexeme);
      }
    } catch (IOException | LexerException e) {
      System.err.println("Error while attempting to perform the lexical analysis: " + e);
      System.exit(3);
    }

  }
}
