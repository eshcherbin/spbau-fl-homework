package ru.spbau.eshcherbin.fl.hw7;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.CharStreams;
import org.junit.Test;
import ru.spbau.eshcherbin.fl.hw7.ast.*;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class LParserTest {

  @Test
  public void testExpression() throws Exception {
    assertThat(parseExpression("x"), is(new AstVariableAccessExpression("x", 1, 0)));
    AstExpression actual = parseExpression("117");
    assertThat(actual, is(new AstDecimalIntegerLiteralExpression("117", 1, 0)));
    assertThat(parseExpression("1.0"),
        is(new AstDecimalFloatingPointLiteralExpression("1.0", 1, 0)));
    assertThat(parseExpression("(a + 23)"), is(
        new AstBinaryOperationExpression(
            new AstVariableAccessExpression("a", 1, 1),
            new AstDecimalIntegerLiteralExpression("23", 1, 5),
            "+",
            1,
            0
        )
    ));
    assertThat(parseExpression("p(x,y)"), is(
        new AstFunctionCallExpression(
            "p",
            Arrays.asList(
                new AstVariableAccessExpression("x", 1, 2),
                new AstVariableAccessExpression("y", 1, 4)
            ),
            1,
            0
        )
    ));
    assertThat(parseExpression("(p(x,y))"), is(
        new AstFunctionCallExpression(
            "p",
            Arrays.asList(
                new AstVariableAccessExpression("x", 1, 3),
                new AstVariableAccessExpression("y", 1, 5)
            ),
            1,
            1
        )
    ));
  }

  @Test
  public void testStatement() throws Exception {
    assertThat(parseStatement("p(x,y)"), is(
        new AstExpressionStatement(
            new AstFunctionCallExpression(
                "p",
                Arrays.asList(
                    new AstVariableAccessExpression("x", 1, 2),
                    new AstVariableAccessExpression("y", 1, 4)
                ),
                1,
                0
            ),
            1,
            0
        )
    ));
    assertThat(parseStatement("x:=1"), is(
        new AstAssignmentStatement(
            "x",
            new AstDecimalIntegerLiteralExpression("1", 1, 3),
            1,
            0
        )
    ));
    assertThat(parseStatement("return x"), is(
        new AstReturnStatement(
            new AstVariableAccessExpression("x", 1, 7),
            1,
            0
        )
    ));
    assertThat(parseStatement("write x"), is(
        new AstWriteStatement(
            new AstVariableAccessExpression("x", 1, 6),
            1,
            0
        )
    ));
    assertThat(parseStatement("read x"), is(
        new AstWriteStatement(
            new AstVariableAccessExpression("x", 1, 6),
            1,
            0
        )
    ));
  }

  private AstExpression parseExpression(String string) {
    return AstNodes.fromContext(parse(string).expression());
  }

  private AstStatement parseStatement(String string) {
    return AstNodes.fromContext(parse(string).statement());
  }

  private LParser parse(String string) {
    return new LParser(new BufferedTokenStream(new LLexer(CharStreams.fromString(string))));
  }
}
