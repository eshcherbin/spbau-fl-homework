package ru.spbau.eshcherbin.homework.fl.hw4;

import ru.spbau.eshcherbin.homework.fl.hw4.lexicon.*;
import ru.spbau.eshcherbin.homework.fl.hw4.lexicon.KeyWord.KeyWordType;
import ru.spbau.eshcherbin.homework.fl.hw4.lexicon.Operator.OperatorType;
import ru.spbau.eshcherbin.homework.fl.hw4.lexicon.Delimiter.DelimiterType;

%%

%class LLexer
%unicode
%line
%column

%yylexthrow LexerException

%{
    private int yyline;
    private int yycolumn;

    private KeyWord buildKeyWord(KeyWordType type) {
        return new KeyWord(type, yyline, yycolumn, yylength());
    }

    private Operator buildOperator(OperatorType type) {
        return new Operator(type, yyline, yycolumn, yylength());
    }

    private Delimiter buildDelimiter(DelimiterType type) {
        return new Delimiter(type, yyline, yycolumn, yylength());
    }
%}

%type Lexeme

LineTerminator = \r | \n | \r\n
WhiteSpace = {LineTerminator} | [ \t\f]

Comment = "//" [^\r\n]*

Identifier = [_a-z] [_a-z0-9]*

Sign = [+-]
ExponentIndicator = [eE]

DigitsOrUnderscores = [_0-9]+
Digits = [0-9] | [0-9] {DigitsOrUnderscores}? [0-9]
SignedInteger = {Sign}? {Digits}
ExponentPart = {ExponentIndicator} {SignedInteger}

DecimalIntegerLiteral = 0 | [1-9] {Digits}? | [1-9] "_"+ {Digits}
DecimalFloatingPointLiteral =
    {Digits} "." {Digits}? {ExponentPart}? |
    "." {Digits} {ExponentPart}? |
    {Digits} {ExponentPart}?

%%

{WhiteSpace} { }

{Comment} { return new Comment(yytext().toString().substring(2), yyline, yycolumn, yylength()); }

/* keywords */
"if"    { return new KeyWord(KeyWordType.IF, yyline, yycolumn, yylength()); }
"then"  { return new KeyWord(KeyWordType.THEN, yyline, yycolumn, yylength()); }
"else"  { return new KeyWord(KeyWordType.ELSE, yyline, yycolumn, yylength()); }
"while" { return new KeyWord(KeyWordType.WHILE, yyline, yycolumn, yylength()); }
"do"    { return new KeyWord(KeyWordType.DO, yyline, yycolumn, yylength()); }
"read"  { return new KeyWord(KeyWordType.READ, yyline, yycolumn, yylength()); }
"write" { return new KeyWord(KeyWordType.WRITE, yyline, yycolumn, yylength()); }
"begin" { return new KeyWord(KeyWordType.BEGIN, yyline, yycolumn, yylength()); }
"end"   { return new KeyWord(KeyWordType.END, yyline, yycolumn, yylength()); }

/* operators */
"+"  { return new Operator(OperatorType.PLUS, yyline, yycolumn, yylength()); }
"-"  { return new Operator(OperatorType.MINUS, yyline, yycolumn, yylength()); }
"*"  { return new Operator(OperatorType.ASTERISK, yyline, yycolumn, yylength()); }
"/"  { return new Operator(OperatorType.SLASH, yyline, yycolumn, yylength()); }
"%"  { return new Operator(OperatorType.PERCENT, yyline, yycolumn, yylength()); }
"==" { return new Operator(OperatorType.EQ, yyline, yycolumn, yylength()); }
"!=" { return new Operator(OperatorType.NEQ, yyline, yycolumn, yylength()); }
">"  { return new Operator(OperatorType.GR, yyline, yycolumn, yylength()); }
">=" { return new Operator(OperatorType.GEQ, yyline, yycolumn, yylength()); }
"<"  { return new Operator(OperatorType.LE, yyline, yycolumn, yylength()); }
"<=" { return new Operator(OperatorType.LEQ, yyline, yycolumn, yylength()); }
"&&" { return new Operator(OperatorType.AND, yyline, yycolumn, yylength()); }
"||" { return new Operator(OperatorType.OR, yyline, yycolumn, yylength()); }

/* delimiters */
"(" { return new Delimiter(DelimiterType.LPAREN, yyline, yycolumn, yylength()); }
")" { return new Delimiter(DelimiterType.RPAREN, yyline, yycolumn, yylength()); }
";" { return new Delimiter(DelimiterType.SEMICOLON, yyline, yycolumn, yylength()); }

"true" { return new BooleanLiteral(true, yyline, yycolumn, yylength()); }
"false" { return new BooleanLiteral(false, yyline, yycolumn, yylength()); }

{DecimalIntegerLiteral} { return new FloatingPointLiteral(Long.parseLong(yytext().toString().replace("_", "")), yyline, yycolumn, yylength()); }
{DecimalFloatingPointLiteral} { return new FloatingPointLiteral(Double.parseDouble(yytext().toString().replace("_", "")), yyline, yycolumn, yylength()); }

{Identifier} { return new Identifier(yytext().toString(), yyline, yycolumn, yylength()); }

[^] { throw new LexerException("Illegal character <" + yytext() + ">"); }
