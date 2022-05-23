package constants;

public enum PageColor {
  BLACK("black"),
  COLOR("color"),
  MIXT("mixt");

  private String _color;

  PageColor(String color) {
    _color = color;
  }

  public String toString() {
    return _color;
  }
}
