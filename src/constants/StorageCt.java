package constants;

public enum StorageCt {
  DATA_REL_PATH("../data");

  private String _path;

  StorageCt(String path) {
    _path = path;
  }

  public String toString() {
    return _path;
  }
}