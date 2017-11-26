package ru.spbau.eshcherbin.fl.hw7;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.CharStreams;
import org.junit.Test;
import ru.spbau.eshcherbin.fl.hw7.ast.*;

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
        new AstReadStatement(
            "x",
            1,
            0
        )
    ));
    assertThat(parseStatement("while 1 { pass }"), is(
        new AstWhileStatement(
            new AstDecimalIntegerLiteralExpression("1", 1, 0),
            new AstBlock(
                Collections.singletonList(
                    new AstExpressionStatement(
                        new AstVariableAccessExpression("pass", 1, 11),
                        1,
                        11
                    )
                ),
                1,
                9
            ),
            1,
            0
        )
    ));
    assertThat(parseStatement("if 1 \n{ pass }"), is(
        new AstIfStatement(
            new AstDecimalIntegerLiteralExpression("1", 1, 0),
            new AstBlock(
                Collections.singletonList(
                    new AstExpressionStatement(
                        new AstVariableAccessExpression("pass", 2, 2),
                        2,
                        0
                    )
                ),
                1,
                9
            ),
            1,
            0
        )
    ));
    assertThat(parseStatement("if 1 \n{ pass }\nelse\n{not_implemented()}"), is(
        new AstIfStatement(
            new AstDecimalIntegerLiteralExpression("1", 1, 0),
            new AstBlock(
                Collections.singletonList(
                    new AstExpressionStatement(
                        new AstVariableAccessExpression("pass", 2, 2),
                        2,
                        0
                    )
                ),
                1,
                9
            ),
            new AstBlock(
                Collections.singletonList(
                    new AstExpressionStatement(
                        new AstFunctionCallExpression(
                            "not_implemented",
                            Collections.emptyList(),
                            4,
                            1),
                        4,
                        1
                    )
                ),
                4,
                0
            ),
            1,
            0
        )
    ));
  }

  @Test
  public void testBlock() throws Exception {
    assertThat(parseBlock("{\nread x\n}"), is(
        new AstBlock(
            Collections.singletonList(
                new AstReadStatement("x", 2, 0)
            ),
            1,
            0
        )
    ));
    assertThat(parseBlock("{\nread x;\nwrite x\n}"), is(
        new AstBlock(
            Arrays.asList(
                new AstReadStatement("x", 2, 0),
                new AstWriteStatement(new AstVariableAccessExpression("x", 3, 6), 3, 0)
            ),
            1,
            0
        )
    ));
    assertThat(isWrongSyntaxBlock("{\nread x\nwrite x\n}"), is(true));
  }

  @Test
  public void testFunctionDefinition() throws Exception {
    assertThat(parseFunctionDefinition("main()\n{\nread x\n}"), is(
        new AstFunctionDefinition(
            "main",
            Collections.emptyList(),
            new AstBlock(
                Collections.singletonList(new AstReadStatement("x", 3, 0)),
                2,
                0
            ),
            1,
            0
        )
    ));
    assertThat(parseFunctionDefinition("main(y,z_1,z_2)\n{\nread x\n}"), is(
        new AstFunctionDefinition(
            "main",
            Arrays.asList("y", "z_1", "z_2"),
            new AstBlock(
                Collections.singletonList(new AstReadStatement("x", 3, 0)),
                2,
                0
            ),
            1,
            0
        )
    ));
  }

  @Test
  public void testProgram() throws Exception {
    assertThat(parseProgram("main()\n{\nread x\n}"), is(
        new AstProgram(
            Collections.singletonList(
                new AstFunctionDefinition(
                    "main",
                    Collections.emptyList(),
                    new AstBlock(
                        Collections.singletonList(new AstReadStatement("x", 3, 0)),
                        2,
                        0
                    ),
                    1,
                    0
                )
            ),
            1,
            0
        )
    ));
    assertThat(parseProgram("main()\n{\nread x\n};\nintersect(y,z_1,z_2)\n{\nread x\n}"), is(
        new AstProgram(
            Arrays.asList(
                new AstFunctionDefinition(
                    "main",
                    Collections.emptyList(),
                    new AstBlock(
                        Collections.singletonList(new AstReadStatement("x", 3, 0)),
                        2,
                        0
                    ),
                    1,
                    0
                ),
                new AstFunctionDefinition(
                    "intersect",
                    Arrays.asList("y", "z_1", "z_2"),
                    new AstBlock(
                        Collections.singletonList(new AstReadStatement("x", 7, 0)),
                        6,
                        0
                    ),
                    5,
                    0
                )
            ),
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

  private AstBlock parseBlock(String string) {
    return AstNodes.fromContext(parse(string).block());
  }

  private AstFunctionDefinition parseFunctionDefinition(String string) {
    return AstNodes.fromContext(parse(string).functionDefinition());
  }

  private AstProgram parseProgram(String string) {
    return AstNodes.fromContext(parse(string).program());
  }

  private boolean isWrongSyntaxBlock(String string) {
    LParser parser = new LParser(new BufferedTokenStream(new LLexer(CharStreams.fromString(string))));
    parser.block();
    return parser.getNumberOfSyntaxErrors() > 0;
  }

  private LParser parse(String string) {
    return new LParser(new BufferedTokenStream(new LLexer(CharStreams.fromString(string))));
  }
}
