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