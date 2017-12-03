# HW7

This is a great parser for the `L` language.

## How to build and run

Run `bash build.sh` to configure and build the executable. 
After that, run `./hw7 <source>` to perform syntactic analysis
of the source code in the `<source>` file. The analyzer will 
write the abstract syntax tree to the standard output.

## Example

`example.L` content:
```
factorial(n) {
    if n == 0 {
        f := 0 // TODO(eshcherbin): fix this bug
    } else {
        m := n - 1;
        factorial(m);
        f := n * f
    }
}

read n;
factorial(n);
write f;
f_1 := f;
factorial(f_1);
write f
```

Build the executable using the instructions above and run `./hw7 example.L`.
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
|   |   assignment {variable name: f; line: 3; column: 8}
|   |   assigned expression:
|   |   |   integer literal {value: 0; line: 3; column: 13}
|   else body:
|   |   delimited statements {line: 5; column: 8}
|   |   first statement:
|   |   |   delimited statements {line: 5; column: 8}
|   |   |   first statement:
|   |   |   |   assignment {variable name: m; line: 5; column: 8}
|   |   |   |   assigned expression:
|   |   |   |   |   binary operation {operator: -; line: 5; column: 13}
|   |   |   |   |   first operand:
|   |   |   |   |   |   variable access {variable name: n; line:5; column:13}
|   |   |   |   |   second operand:
|   |   |   |   |   |   integer literal {value: 1; line: 5; column: 17}
|   |   |   second statement:
|   |   |   |   function call {name : factorial; arguments: m; line: 6; column: 8}
|   |   second statement:
|   |   |   assignment {variable name: f; line: 7; column: 8}
|   |   |   assigned expression:
|   |   |   |   binary operation {operator: *; line: 7; column: 13}
|   |   |   |   first operand:
|   |   |   |   |   variable access {variable name: n; line:7; column:13}
|   |   |   |   second operand:
|   |   |   |   |   variable access {variable name: f; line:7; column:17}
statement:
|   delimited statements {line: 11; column: 0}
|   first statement:
|   |   delimited statements {line: 11; column: 0}
|   |   first statement:
|   |   |   delimited statements {line: 11; column: 0}
|   |   |   first statement:
|   |   |   |   delimited statements {line: 11; column: 0}
|   |   |   |   first statement:
|   |   |   |   |   delimited statements {line: 11; column: 0}
|   |   |   |   |   first statement:
|   |   |   |   |   |   read {variable name: n; line: 11; column: 0}
|   |   |   |   |   second statement:
|   |   |   |   |   |   function call {name : factorial; arguments: n; line: 12; column: 0}
|   |   |   |   second statement:
|   |   |   |   |   write {line: 13; column: 0}
|   |   |   |   |   expression:
|   |   |   |   |   |   variable access {variable name: f; line:13; column:6}
|   |   |   second statement:
|   |   |   |   assignment {variable name: f_1; line: 14; column: 0}
|   |   |   |   assigned expression:
|   |   |   |   |   variable access {variable name: f; line:14; column:7}
|   |   second statement:
|   |   |   function call {name : factorial; arguments: f_1; line: 15; column: 0}
|   second statement:
|   |   write {line: 16; column: 0}
|   |   expression:
|   |   |   variable access {variable name: f; line:16; column:6}
```
