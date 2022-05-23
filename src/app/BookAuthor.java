package app;

import constants.AuthorTitle;
import constants.Label;
import interfaces.IAuthor;
import interfaces.IBookAuthor;

public class BookAuthor extends Author implements IBookAuthor {
  private AuthorTitle _authorTitle;
  private static final Label _label  = Label.BOOK_AUTHOR;

  public BookAuthor(IAuthor author) {
    super(author.getPerson());
  }

  public void setAuthorTitle(AuthorTitle authorTitle) {
    _authorTitle = authorTitle;
  }

  public AuthorTitle getAuthorTitle() {
    return _authorTitle;
  }

  public Label getLabel() {
    return BookAuthor._label;
  }
}
