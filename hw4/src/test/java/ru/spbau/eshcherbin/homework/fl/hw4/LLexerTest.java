package ru.spbau.eshcherbin.homework.fl.hw4;

import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;
import ru.spbau.eshcherbin.homework.fl.hw4.lexicon.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static ru.spbau.eshcherbin.homework.fl.hw4.lexicon.KeyWord.KeyWordType.*;
import static ru.spbau.eshcherbin.homework.fl.hw4.lexicon.Operator.OperatorType.*;
import static ru.spbau.eshcherbin.homework.fl.hw4.lexicon.Delimiter.DelimiterType.*;

public class LLexerTest {
  private LLexer lexer;

  @Before
  public void setUp() throws Exception {
    lexer = new LLexer(null);
  }

  @Test
  public void testWhiteSpaces() throws Exception {
    List<Lexeme> lexemes = runLexer("\f \f    \t\t\t\t\n \n\n\r \r\n\n\t\t\t   ");

    assertThat(lexemes, is(empty()));
  }

  @Test
  public void testComments() throws Exception {
    List<Lexeme> lexemes = runLexer("// asdf  \n\r\n     \t \f//hello if 1// + .5e+3\n\n");

    assertThat(lexemes, is(equalTo(ImmutableList.of(
        new Comment(" asdf  ", 0, 0, 9),
        new Comment("hello if 1// + .5e+3", 3, 0, 22)
    ))));
  }

  @Test
  public void testKeyWords() throws Exception {
    List<Lexeme> lexemes = runLexer("//if (happy) then clapYourHands; end\n" +
        "if\tthen\t\telse   end \r\n" +
        "while\n" +
        "do\n" +
        "read\n" +
        "write\n" +
        "begin");

    assertThat(lexemes, is(equalTo(ImmutableList.of(
        new Comment("if (happy) then clapYourHands; end", 0, 0, 36),
        new KeyWord(IF, 1, 0, 2),
        new KeyWord(THEN, 1, 3, 4),
        new KeyWord(ELSE, 1, 9, 4),
        new KeyWord(END, 1, 16, 3),
        new KeyWord(WHILE, 2, 0, 5),
        new KeyWord(DO, 3, 0, 2),
        new KeyWord(READ, 4, 0, 4),
        new KeyWord(WRITE, 5, 0, 5),
        new KeyWord(BEGIN, 6, 0, 5)
    ))));
  }

  @Test
  public void testBooleanLiterals() throws Exception {
    List<Lexeme> lexemes = runLexer("if true then false");

    assertThat(lexemes, is(equalTo(ImmutableList.of(
        new KeyWord(IF, 0, 0, 2),
        new BooleanLiteral(true, 0, 3, 4),
        new KeyWord(THEN, 0, 8, 4),
        new BooleanLiteral(false, 0,13, 5)
    ))));
  }

  @Test
  public void testFloatingPointLiterals() throws Exception {
    List<Lexeme> lexemes = runLexer("117\n" +
        "117117117117\n" +
        "225\n" +
        "22.5\n" +
        "2________________________________2.5\n" +
        "1_0_1__1_0_1\n" +
        "1.2e2\n" +
        "1.2e+2\n" +
        "1.2e-2\n" +
        "1.2e+1_0\n" +
        ".5E1\n");

    assertThat(lexemes, is(equalTo(ImmutableList.of(
        new FloatingPointLiteral(117, 0, 0, 3),
        new FloatingPointLiteral(117117117117L, 1, 0, 12),
        new FloatingPointLiteral(225, 2, 0, 3),
        new FloatingPointLiteral(22.5, 3, 0, 4),
        new FloatingPointLiteral(22.5, 4, 0, 36),
        new FloatingPointLiteral(101101, 5, 0, 12),
        new FloatingPointLiteral(120, 6, 0, 5),
        new FloatingPointLiteral(120, 7, 0, 6),
        new FloatingPointLiteral(.012, 8, 0, 6),
        new FloatingPointLiteral(12000000000D, 9, 0, 8),
        new FloatingPointLiteral(5, 10, 0, 4)
    ))));
  }

  @Test
  public void testOperators() throws Exception {
    List<Lexeme> lexemes = runLexer("+\n" +
        "-\n" +
        "*\n" +
        "/\n" +
        "%\n" +
        "==\n" +
        "!=\n" +
        ">\n" +
        ">=\n" +
        "<\n" +
        "<=\n" +
        "&&\n" +
        "||\n");

    assertThat(lexemes, is(equalTo(ImmutableList.of(
        new Operator(PLUS, 0, 0, 1),
        new Operator(MINUS, 1, 0, 1),
        new Operator(ASTERISK, 2, 0, 1),
        new Operator(SLASH, 3, 0, 1),
        new Operator(PERCENT, 4, 0, 1),
        new Operator(EQ, 5, 0, 2),
        new Operator(NEQ, 6, 0, 2),
        new Operator(GR, 7, 0, 1),
        new Operator(GEQ, 8, 0, 2),
        new Operator(LE, 9, 0, 1),
        new Operator(LEQ, 10, 0, 2),
        new Operator(AND, 11, 0, 2),
        new Operator(OR, 12, 0, 2)
    ))));
  }

  @Test
  public void testDelimiters() throws Exception {
    List<Lexeme> lexemes = runLexer("(\n" +
        ")\n" +
        ";");

    assertThat(lexemes, is(equalTo(ImmutableList.of(
        new Delimiter(LPAREN, 0, 0, 1),
        new Delimiter(RPAREN, 1, 0, 1),
        new Delimiter(SEMICOLON, 2, 0, 1)
    ))));
  }

  @Test
  public void testIdentifiers() throws Exception {
    List<Lexeme> lexemes = runLexer("x\n" +
        "var\n" +
        "_begin\n" +
        "end_\n" +
        "if_you_are_happy_clap_your_hands\n" +
        "x1 y1 x2 y2\n" +
        "__name__ == __main__");

    assertThat(lexemes, is(equalTo(ImmutableList.of(
        new Identifier("x", 0, 0, 1),
        new Identifier("var", 1, 0, 3),
        new Identifier("_begin", 2, 0, 6),
        new Identifier("end_", 3, 0, 4),
        new Identifier("if_you_are_happy_clap_your_hands", 4, 0, 32),
        new Identifier("x1", 5, 0, 2),
        new Identifier("y1", 5, 3, 2),
        new Identifier("x2", 5, 6, 2),
        new Identifier("y2", 5, 9, 2),
        new Identifier("__name__", 6, 0, 8),
        new Operator(EQ, 6, 9, 2),
        new Identifier("__main__", 6, 12, 8)
    ))));
  }

  @Test
  public void testFunctionalFibonacci() throws Exception {
    List<Lexeme> lexemes = runLexer("fib n ==> fib_helper n 0 1;\n" +
            "fib_helper 0 a _ ==> a;\n" +
            "fib_helper n a b ==> (n-1) b (a+b);");

    assertThat(lexemes, is(equalTo(ImmutableList.of(
        new Identifier("fib", 0, 0, 3),
        new Identifier("n", 0, 4, 1),
        new Operator(EQ, 0, 6, 2),
        new Operator(GR, 0, 8, 1),
        new Identifier("fib_helper", 0, 10, 10),
        new Identifier("n", 0, 21, 1),
        new FloatingPointLiteral(0, 0, 23, 1),
        new FloatingPointLiteral(1, 0, 25, 1),
        new Delimiter(SEMICOLON, 0, 26, 1),
        new Identifier("fib_helper", 1, 0, 10),
        new FloatingPointLiteral(0, 1, 11, 1),
        new Identifier("a", 1, 13, 1),
        new Identifier("_", 1, 15, 1),
        new Operator(EQ, 1, 17, 2),
        new Operator(GR, 1, 19, 1),
        new Identifier("a", 1, 21, 1),
        new Delimiter(SEMICOLON, 1, 22, 1),
        new Identifier("fib_helper", 2, 0, 10),
        new Identifier("n", 2, 11, 1),
        new Identifier("a", 2, 13, 1),
        new Identifier("b", 2, 15, 1),
        new Operator(EQ, 2, 17, 2),
        new Operator(GR, 2, 19, 1),
        new Delimiter(LPAREN, 2, 21, 1),
        new Identifier("n", 2, 22, 1),
        new Operator(MINUS, 2, 23, 1),
        new FloatingPointLiteral(1, 2, 24, 1),
        new Delimiter(RPAREN, 2, 25, 1),
        new Identifier("b", 2, 27, 1),
        new Delimiter(LPAREN, 2, 29, 1),
        new Identifier("a", 2, 30, 1),
        new Operator(PLUS, 2, 31, 1),
        new Identifier("b", 2, 32, 1),
        new Delimiter(RPAREN, 2, 33, 1),
        new Delimiter(SEMICOLON, 2, 34, 1)
    ))));
  }

  private List<Lexeme> runLexer(String source) throws Exception {
    lexer.reset(source, 0, source.length(), LLexer.YYINITIAL);
    List<Lexeme> lexemes = new ArrayList<>();
    Lexeme lexeme = lexer.yylex();
    while (lexeme != null) {
      lexemes.add(lexeme);
      lexeme = lexer.yylex();
    }
    return lexemes;
  }
}