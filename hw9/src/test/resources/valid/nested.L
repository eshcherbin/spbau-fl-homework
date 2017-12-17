main(args) {
    if len(args) != 1 {
        write one_argument_expected
    } else {
        if get(args, 0) == nested_filename {
            while 1 {
                be_happy()
            };
            while 0 {
                // shoudn't happen
                // do nothing
            }
        }
    }
}

if name == main {
    main()
}