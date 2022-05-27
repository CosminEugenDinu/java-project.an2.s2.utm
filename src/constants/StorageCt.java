package constants;

public enum StorageCt {
  DATA_REL_PATH("../data"),
  BOOK_FILE("book.csv"),
  CONTRIB_FILE("contrib.csv"),
  PAGE_FILE("page.csv"),
  PERS_FILE("pers.csv"),
  CSV_SEP(",");

  private String _path;

  StorageCt(String path) {
    _path = path;
  }

  public String toString() {
    return _path;
  }
}