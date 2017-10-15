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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;

    Comment comment = (Comment) o;

    return getCommentText() != null ?
        getCommentText().equals(comment.getCommentText()) :
        comment.getCommentText() == null;
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (getCommentText() != null ? getCommentText().hashCode() : 0);
    return result;
  }
}
