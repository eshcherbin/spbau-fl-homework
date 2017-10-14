package ru.spbau.eshcherbin.homework.fl.hw4.lexicon;

public class Identifier extends Lexeme {
    private static final String LEXEME_KIND_IDENTIFIER = "Identifier";

    private String name;

    public Identifier(String name, int line, int column, int length) {
        super(line, column, length);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    protected String getLexemeDetails() {
        return LEXEME_KIND_IDENTIFIER +
                "(\"" +
                getName() +
                "\")";
    }
}
