# HW9

This is a great parser for the `L` language.

## How to build and run

Run `bash build.sh` to configure and build the executable. 
After that, run `./hw9 <source>` to perform syntactic analysis
of the source code in the `<source>` file. The analyzer will 
write the abstract syntax tree to the standard output.

## Example

`example.L` content:
```
factorial(n) {
    if n == 0 {
        factorial := 0 // TODO(eshcherbin): fix this bug
    } else {
        factorial := n * factorial(n - 1)
    }
}

read n;
write factorial(n);
write factorial(
    factorial(
        n
    )
)
```

Build the executable using the instructions above and run `./hw9 example.L`.
You should see the following output:

```
program:
function definition {name: factorial; arguments: n; line: 1; column: 0}
body:
|   if {line: 2; column: 4}
|   condition:
|   |   binary operation {operator: ==; line: 2; column: 7}
|   |   first operand:
|   |   |   variable access {variable name: n; line:2; column:7}
|   |   second operand:
|   |   |   integer literal {value: 0; line: 2; column: 12}
|   then body:
|   |   assignment {variable name: factorial; line: 3; column: 8}
|   |   assigned expression:
|   |   |   integer literal {value: 0; line: 3; column: 21}
|   else body:
|   |   assignment {variable name: factorial; line: 5; column: 8}
|   |   assigned expression:
|   |   |   binary operation {operator: *; line: 5; column: 21}
|   |   |   first operand:
|   |   |   |   variable access {variable name: n; line:5; column:21}
|   |   |   second operand:
|   |   |   |   function call {name : factorial; line: 5; column: 25}
|   |   |   |   argument0:
|   |   |   |   |   binary operation {operator: -; line: 5; column: 35}
|   |   |   |   |   first operand:
|   |   |   |   |   |   variable access {variable name: n; line:5; column:35}
|   |   |   |   |   second operand:
|   |   |   |   |   |   integer literal {value: 1; line: 5; column: 39}
statement:
|   delimited statements {line: 9; column: 0}
|   statement0:
|   |   read {variable name: n; line: 9; column: 0}
|   statement1:
|   |   delimited statements {line: 10; column: 0}
|   |   statement0:
|   |   |   write {line: 10; column: 0}
|   |   |   expression:
|   |   |   |   function call {name : factorial; line: 10; column: 6}
|   |   |   |   argument0:
|   |   |   |   |   variable access {variable name: n; line:10; column:16}
|   |   statement1:
|   |   |   write {line: 11; column: 0}
|   |   |   expression:
|   |   |   |   function call {name : factorial; line: 11; column: 6}
|   |   |   |   argument0:
|   |   |   |   |   function call {name : factorial; line: 12; column: 4}
|   |   |   |   |   argument0:
|   |   |   |   |   |   variable access {variable name: n; line:13; column:8}
```
