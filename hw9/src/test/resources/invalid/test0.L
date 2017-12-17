factorial(n) {
    if n == 0 {
        factorial := 0 // TODO(eshcherbin): fix this bug
    } else {
        factorial := * factorial(n - 1)
    }
}
