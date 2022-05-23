package constants;

public enum Label {
  BOOK("book"),
  ARTICLE("article"),
  AUTHOR("author"),
  BOOK_AUTHOR("bookAuthor"),
  READER("reader");

  private String _label;

  Label(String label) {
    _label = label;
  }

  public String toString() {
    return _label;
  }
}
