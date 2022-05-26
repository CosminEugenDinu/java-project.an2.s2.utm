package app;

import constants.PageColor;
import interfaces.IPage;

public class Page implements IPage {
  private int _width;
  private int _height;
  private PageColor _color;

  public Page(int width, int height, PageColor color) {
    _width = width;
    _height = height;
    _color = color;
  };

  public int getWidth() {
    return _width;
  }

  public int getHeight() {
    return _height;
  };

  public PageColor getColor() {
    return _color;
  };
}
