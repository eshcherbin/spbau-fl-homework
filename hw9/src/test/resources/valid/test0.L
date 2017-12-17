factorial(n) {
    if n == 0 {
        factorial := 0 // TODO(eshcherbin): fix this bug
    } else {
        factorial := n * factorial(n - 1)
    }
}
