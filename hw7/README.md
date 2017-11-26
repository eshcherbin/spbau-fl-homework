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
    if (n == 0) {
        return 0 // TODO(eshcherbin): fix this bug
    } else {
        return (n * factorial((n - 1)))
    }
};
main() {
    read n;
    f := factorial(n);
    write f;
    write factorial(
        factorial(
            f
        )
    )
}
```

Build the executable using the instructions above and run `./hw7 example.L`.
You should see the following output:

```
function definition {name: factorial; arguments: n; line: 1; column: 0}
body:
block start {line: 1; column: 13}
|   if {line: 2; column: 4}
|   |   condition:
|   |   |   binary operation {operator: ==; line: 2; column: 7}
|   |   |   |   left operand:
|   |   |   |   |   variable access {variable name: n; line:2; column:8}
|   |   |   |   right operand:
|   |   |   |   |   integer literal {value: 0; line: 2; column: 13}
|   |   then body:
|   |   block start {line: 2; column: 16}
|   |   |   return {line: 3; column: 8}
|   |   |   |   integer literal {value: 0; line: 3; column: 15}
|   |   else body:
|   |   block start {line: 4; column: 11}
|   |   |   return {line: 5; column: 8}
|   |   |   |   binary operation {operator: *; line: 5; column: 15}
|   |   |   |   |   left operand:
|   |   |   |   |   |   variable access {variable name: n; line:5; column:16}
|   |   |   |   |   right operand:
|   |   |   |   |   |   function call {name : factorial; line: 5; column: 20}
|   |   |   |   |   |   |   argument0:
|   |   |   |   |   |   |   |   binary operation {operator: -; line: 5; column: 30}
|   |   |   |   |   |   |   |   |   left operand:
|   |   |   |   |   |   |   |   |   |   variable access {variable name: n; line:5; column:31}
|   |   |   |   |   |   |   |   |   right operand:
|   |   |   |   |   |   |   |   |   |   integer literal {value: 1; line: 5; column: 35}
function definition {name: main; no arguments; line: 8; column: 0}
body:
block start {line: 8; column: 7}
|   read {variable name: n; line: 9; column: 4}
|   assignment {variable name: f; line: 10; column: 4}
|   |   assigned expression:
|   |   |   function call {name : factorial; line: 10; column: 9}
|   |   |   |   argument0:
|   |   |   |   |   variable access {variable name: n; line:10; column:19}
|   write {line: 11; column: 4}
|   |   variable access {variable name: f; line:11; column:10}
|   write {line: 12; column: 4}
|   |   function call {name : factorial; line: 12; column: 10}
|   |   |   argument0:
|   |   |   |   function call {name : factorial; line: 13; column: 8}
|   |   |   |   |   argument0:
|   |   |   |   |   |   variable access {variable name: f; line:14; column:12}
```
