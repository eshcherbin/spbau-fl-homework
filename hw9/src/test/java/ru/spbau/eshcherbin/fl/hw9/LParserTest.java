package ru.spbau.eshcherbin.fl.hw9;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.CharStreams;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.junit.Test;
import ru.spbau.eshcherbin.fl.hw9.ast.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class LParserTest {
  @Test
  public void testProgram() throws Exception {
    testValid(getClass().getResource("/valid/").getPath(), LParserTest::parseProgram);
    testInvalid(getClass().getResource("/invalid/").getPath(), LParserTest::parseProgram);
  }
  private void testValid(String path, Function<String, ? extends AstNode> parseFunction) throws Exception {
    List<String> testNames = Files.list(Paths.get(path))
        .filter(file -> FilenameUtils.getExtension(file.toString()).equals("L"))
        .map(file -> FilenameUtils.getBaseName(file.toString()))
        .collect(Collectors.toList());
    for (String test : testNames) {
      AstTestPrinter printer = new AstTestPrinter();
      AstNode node = parseFunction.apply(
          FileUtils.readFileToString(new File(FilenameUtils.concat(path, test + ".L"))));
      node.accept(printer);
      assertEquals(
          FileUtils.readFileToString(new File(FilenameUtils.concat(path, test + ".ans"))),
          printer.getString());
    }
  }

  private void testInvalid(String path, Function<String, ? extends AstNode> parseFunction) throws Exception {
    List<String> testNames = Files.list(Paths.get(path))
        .filter(file -> FilenameUtils.getExtension(file.toString()).equals("L"))
        .map(file -> FilenameUtils.getBaseName(file.toString()))
        .collect(Collectors.toList());
    for (String test : testNames) {
      try {
        AstNode node = parseFunction.apply(
            FileUtils.readFileToString(new File(FilenameUtils.concat(path, test + ".L"))));
        node.accept(new AstTestPrinter());
        fail("Expected an ParsingException but none was thrown");
      } catch (ParsingException e) {
        // everything is as expected
      }
    }
  }

  private static AstProgram parseProgram(String string) {
    LParser parser = parse(string);
    AstProgram node = AstNodes.fromContext(parser.program());
    if (parser.getNumberOfSyntaxErrors() != 0) {
      throw new ParsingException();
    }
    return node;
  }

  private static LParser parse(String string) {
    return new LParser(new BufferedTokenStream(new LLexer(CharStreams.fromString(string))));
  }
}
