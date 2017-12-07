package ru.spbau.eshcherbin.fl.hw9;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.CharStreams;
import org.junit.Test;
import ru.spbau.eshcherbin.fl.hw9.ast.*;

import java.util.Arrays;
import java.util.Collections;

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
  }

  @Test
  public void testStatement() throws Exception {
    assertThat(parseStatement("p(x,y)"), is(
        new AstFunctionCallStatement(
            new AstFunctionCall(
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
    assertThat(parseStatement("write x"), is(
        new AstWriteStatement(
            new AstVariableAccessExpression("x", 1, 6),
            1,
            0
        )
    ));
    assertThat(parseStatement("read x"), is(
        new AstReadStatement(
            "x",
            1,
            0
        )
    ));
    assertThat(parseStatement("while 1 { pass() }"), is(
        new AstWhileStatement(
            new AstDecimalIntegerLiteralExpression("1", 1, 0),
            new AstFunctionCallStatement(
                new AstFunctionCall(
                    "pass",
                    Collections.emptyList(),
                    1,
                    11
                ),
                1,
                9
            ),
            1,
            9
        )
    ));
    assertThat(parseStatement("if 1 \n{ pass() }"), is(
        new AstIfStatement(
            new AstDecimalIntegerLiteralExpression("1", 1, 0),
            new AstFunctionCallStatement(
                new AstFunctionCall(
                    "pass",
                    Collections.emptyList(),
                    2,
                    0
                ),
                2,
                0
            ),
            1,
            0
        )
    ));
    assertThat(parseStatement("if 1 \n{ pass() }\nelse\n{not_implemented()}"), is(
        new AstIfStatement(
            new AstDecimalIntegerLiteralExpression("1", 1, 0),
            new AstFunctionCallStatement(
                new AstFunctionCall(
                    "pass",
                    Collections.emptyList(),
                    2,
                    0
                ),
                2,
                0
            ),
            new AstFunctionCallStatement(
                new AstFunctionCall(
                    "not_implemented",
                    Collections.emptyList(),
                    4,
                    1)
                ,
                4,
                1
            ),
            1,
            0
        )
    ));
  }

  @Test
  public void testFunctionDefinition() throws Exception {
    assertThat(parseFunctionDefinition("main()\n{\nread x\n}"), is(
        new AstFunctionDefinition(
            "main",
            Collections.emptyList(),
            new AstReadStatement("x", 3, 0),
            1,
            0
        )
    ));
    assertThat(parseFunctionDefinition("main(y,z_1,z_2)\n{\nread x\n}"), is(
        new AstFunctionDefinition(
            "main",
            Arrays.asList("y", "z_1", "z_2"),
                new AstReadStatement("x", 3, 0),
            1,
            0
        )
    ));
  }

  @Test
  public void testProgram() throws Exception {
    assertThat(parseProgram("read x"), is(
        new AstProgram(
            Collections.emptyList(),
            new AstReadStatement("x", 3, 0),
            1,
            0
        )
    ));
    assertThat(parseProgram("intersect(y,z_1,z_2)\n{\nread x\n}\nread x"), is(
        new AstProgram(
            Collections.singletonList(
                new AstFunctionDefinition(
                    "intersect",
                    Arrays.asList("y", "z_1", "z_2"),
                    new AstReadStatement("x", 3, 0),
                    1,
                    0
                )
            ),
            new AstReadStatement("x", 5, 0),
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

  private AstFunctionDefinition parseFunctionDefinition(String string) {
    return AstNodes.fromContext(parse(string).functionDefinition());
  }

  private AstProgram parseProgram(String string) {
    return AstNodes.fromContext(parse(string).program());
  }

  private LParser parse(String string) {
    return new LParser(new BufferedTokenStream(new LLexer(CharStreams.fromString(string))));
  }
}
