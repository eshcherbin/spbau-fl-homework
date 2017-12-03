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
