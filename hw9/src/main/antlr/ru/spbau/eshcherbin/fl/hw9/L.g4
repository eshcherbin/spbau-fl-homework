grammar L;

program
    :   (functionDefinitions += functionDefinition)* statement? EOF
    ;

functionDefinition
    :   functionName = IDENTIFIER '(' (argumentNames += IDENTIFIER (',' argumentNames += IDENTIFIER)*)? ')'
        '{' functionBody = statement? '}'
    ;

functionCall
    :   functionName = IDENTIFIER '(' (arguments += expression (',' arguments += expression)*)? ')'
    ;

statement
    :   IDENTIFIER ':=' expression
        # assignmentStatement

    |   functionCall
        # functionCallStatement

    |   statements += statement (';' statements += statement)+
        # delimitedStatements

    |   'write' expression
        # writeStatement

    |   'read' IDENTIFIER
        # readStatement

    |   'while' condition = expression '{' body = statement? '}'
        # whileStatement

    |   'if' condition = expression '{' thenBody = statement? '}' ('else' '{' elseBody = statement? '}')?
        # ifStatement
    ;

expression
    :   '(' expression ')'
        # expressionInParentheses

    |   functionCall
        # functionCallExpression

    |   IDENTIFIER
        # variableAccessExpression

    |   DECIMAL_INTEGER_LITERAL
        # decimalIntegerLiteralExpression

    |   DECIMAL_FLOATING_POINT_LITERAL
        # decimalFloatingPointLiteralExpression

    |   firstOperand = expression operator = ('*' | '/' | '%') secondOperand = expression
        # binaryOperationExpression

    |   firstOperand = expression operator = ('+' | '-') secondOperand = expression
        # binaryOperationExpression

    |   firstOperand = expression operator = ('<' | '>' | '<=' | '>=') secondOperand = expression
        # binaryOperationExpression

    |   firstOperand = expression operator = ('==' | '!=') secondOperand = expression
        # binaryOperationExpression

    |   firstOperand = expression operator = '&&' secondOperand = expression
        # binaryOperationExpression

    |   firstOperand = expression operator = '||' secondOperand = expression
        # binaryOperationExpression
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
