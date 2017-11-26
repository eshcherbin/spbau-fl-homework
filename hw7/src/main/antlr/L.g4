grammar L;

program
    :   functionDefinitions += functionDefinition (';' functionDefinitions += functionDefinition)*
    ;

functionDefinition
    :   functionName = IDENTIFIER '(' (argumentNames += IDENTIFIER (',' argumentNames += IDENTIFIER)*) ')'
        functionBody = block
    ;

block
    :   '{' statements += statement (';' statements += statement)* '}'
    ;

statement
    :   expression
        # expressionStatement

    |   IDENTIFIER ':=' expression
        # assignmentStatement

    |   'return' expression
        # returnStatement

    |   'write' '(' expression ')'
        # writeStatement

    |   'read' '(' IDENTIFIER ')'
        # readStatement

    |   'if' '(' condition = expression ')' thenBody = block ('else' elseBody = block)?
        # ifStatement

    |   'while' '(' condition = expression ')' body = block
        # whileStatement
    ;

expression
    :   '(' expression ')'
        # expressionInParentheses

    |   IDENTIFIER
        # variableAccessExpression

    |   IDENTIFIER '(' (arguments += expression (',' arguments += expression)*)? ')'
        # functionCallExpression

    |   DECIMAL_INTEGER_LITERAL
        # decimalIntegerLiteralExpression

    |   DECIMAL_FLOATING_POINT_LITERAL
        # decimalFloatingPointLiteralExpression

    |   '('
        leftOperand = expression
        BINARY_OPERATION
        rightOperand = expression
        ')'
        # binaryOperationExpression
    ;


BINARY_OPERATION
    :   '+'
    |   '-'
    |   '*'
    |   '/'
    |   '%'
    |   '=='
    |   '!='
    |   '>'
    |   '>='
    |   '<'
    |   '<='
    |   '&&'
    |   '||'
    ;

LINE_TERMINATOR
    :   '\r'? '\n' -> skip
    ;

WHITESPACE
    :   [ \t\f] -> skip
    ;

COMMENT
    :   '//' .*? (LINE_TERMINATOR | EOF) -> skip
    ;

fragment ZERO_DIGIT
    :   '0'
    ;

fragment NON_ZERO_DIGIT
    :   [1-9]
    ;

fragment DIGIT
    :   ZERO_DIGIT
    |   NON_ZERO_DIGIT
    ;

fragment LETTER
    :   [a-zA-Z]
    ;

fragment UNDERSCORE
    :   '_'
    ;

IDENTIFIER
    :   (LETTER | UNDERSCORE) (LETTER | UNDERSCORE | DIGIT)*
    ;

fragment SIGN
    :   [-+]
    ;

fragment EXPONENT_INDICATOR
    :   [eE]
    ;

fragment DIGITS_OR_UNDERSCORES
    :   (DIGIT | UNDERSCORE)+
    ;

fragment DIGITS
    :   DIGIT
    |   DIGIT DIGITS_OR_UNDERSCORES? DIGIT
    ;

fragment SIGNED_INTEGER
    :   SIGN? DIGITS
    ;

fragment EXPONENT_PART
    :   EXPONENT_INDICATOR SIGNED_INTEGER
    ;

DECIMAL_INTEGER_LITERAL
    :   '0'
    |   NON_ZERO_DIGIT DIGITS?
    |   NON_ZERO_DIGIT UNDERSCORE+ DIGITS
    ;

DECIMAL_FLOATING_POINT_LITERAL
    :   DIGITS '.' DIGITS? EXPONENT_PART?
    |   '.' DIGITS EXPONENT_PART?
    |   DIGITS EXPONENT_PART?
    ;