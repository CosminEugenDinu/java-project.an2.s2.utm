package app;

import constants.Label;
import interfaces.IAuthor;
import interfaces.IBookAuthor;
import interfaces.IPerson;

public class BookAuthor extends Author implements IBookAuthor {
  private String _authorTitle;
  private static final Label _label = Label.BOOK_AUTHOR;

  public BookAuthor(IAuthor author) {
    super(author.getPerson());
  }

  public void setAuthorTitle(String authorTitle) {
    _authorTitle = authorTitle;
  }

  public String getAuthorTitle() {
    return _authorTitle.toString();
  }

  public Label getLabel() {
    return BookAuthor._label;
  }

  public String toString() {
    final String EOL = System.lineSeparator();
    IPerson p = getPerson();
    return "{" + EOL +
        "email: " + p.getEmail() + EOL +
        "name: " + p.getName() + EOL +
        "age: " + p.getAge() + EOL +
        "title: " + getAuthorTitle() + EOL +
        "}";
  }
}
