package ru.spbau.eshcherbin.homework.fl.hw4.lexicon;

public class Comment extends Lexeme {
    private static final String LEXEME_KIND_COMMENT = "Comment";

    private String commentText;

    public Comment(String commentText, int line, int column, int length) {
        super(line, column, length);
        this.commentText = commentText;
    }

    public String getCommentText() {
        return commentText;
    }

    @Override
    protected String getLexemeDetails() {
        return LEXEME_KIND_COMMENT +
                "(\"" +
                getCommentText() +
                "\")";
    }
}
