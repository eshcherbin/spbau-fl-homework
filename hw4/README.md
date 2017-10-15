# HW4

This is a great lexer for the `L` language.

## How to build and run

Run `bash build.sh` to configure and build the executable. 
After that, run `./hw4 <source>` to perform lexical analysis
of the source code in the `<source>` file.

## Example

`example.L` content:
```
read x; if y + 1 == x then write y else write x
```

Build the executable using the instructions above and run `./hw4 example.L`.
You should see the following output:

```
{KeyWord(READ), 0, 0, 3}
{Identifier("x"), 0, 5, 5}
{Delimiter(SEMICOLON), 0, 6, 6}
{KeyWord(IF), 0, 8, 9}
{Identifier("y"), 0, 11, 11}
{Operator(PLUS), 0, 13, 13}
{FloatingPointLiteral(1.0), 0, 15, 15}
{Operator(EQ), 0, 17, 18}
{Identifier("x"), 0, 20, 20}
{KeyWord(THEN), 0, 22, 25}
{KeyWord(WRITE), 0, 27, 31}
{Identifier("y"), 0, 33, 33}
{KeyWord(ELSE), 0, 35, 38}
{KeyWord(WRITE), 0, 40, 44}
{Identifier("x"), 0, 46, 46}
```
