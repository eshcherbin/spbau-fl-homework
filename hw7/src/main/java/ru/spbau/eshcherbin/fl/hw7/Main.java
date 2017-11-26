package ru.spbau.eshcherbin.fl.hw7;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import ru.spbau.eshcherbin.fl.hw7.ast.AstNodes;
import ru.spbau.eshcherbin.fl.hw7.ast.AstProgram;

import java.io.IOException;

public class Main {
  public static void main(String[] args) {
    if (args.length != 1) {
      System.err.println("One command line argument expected, but " + args.length + " found");
      System.exit(1);
    }

    CharStream inputStream = null;
    try {
      inputStream = CharStreams.fromFileName(args[0]);
    } catch (IOException e) {
      System.err.println("Error while attempting to read the given file: " + e);
      System.exit(2);
    }

    LLexer lexer = new LLexer(inputStream);
    LParser parser = new LParser(new BufferedTokenStream(lexer));
    AstProgram program = AstNodes.fromContext(parser.program());
    if (parser.getNumberOfSyntaxErrors() == 0) {
      program.accept(new AstPrinter());
    } else {
      System.err.println("Error while parsing the given file, aborting");
      System.exit(3);
    }
  }
}
