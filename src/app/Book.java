package app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

import interfaces.IAuthor;
import interfaces.IBook;
import interfaces.IBookAuthor;
import interfaces.IPage;
import interfaces.IPerson;
import constants.Label;
import constants.PageColor;
import constants.AuthorTitle;

public class Book implements IBook {
  private static final Label _label = Label.BOOK;
  private int _id;
  private String _title;
  private ArrayList<IBookAuthor> _authors = new ArrayList<IBookAuthor>();
  private HashMap<String, IPerson> _readers = new HashMap<>();
  private HashMap<Integer, IPage> _pages = new HashMap<>();

  public Book(String title) {
    _title = title;
  }

  public int getId() {
    return _id;
  }

  public int setId(int id) {
    _id = id;
    return _id;
  }

  public void setTitle(String title) {
    _title = title;
  }

  public String getTitle() {
    return _title;
  }

  public int addAuthor(IAuthor author, AuthorTitle authorTitle) {
    BookAuthor bookAuthor = new BookAuthor(author);
    _authors.add(bookAuthor);
    return _authors.size();
  }

  public int addBookAuthors(IBookAuthor[] bookAuthors) {
    for (IBookAuthor bookAuthor : bookAuthors) {
      _authors.add(bookAuthor);
    }
    return _authors.size();
  }

  public IAuthor[] getAuthors() {
    IAuthor[] authors = new IAuthor[_authors.size()];
    int i = 0;
    for (IAuthor a : _authors) {
      authors[++i] = a;
    }
    return authors;
  };

  public int addReader(IPerson reader) {
    _readers.put(reader.getEmail(), reader);
    return _readers.size();
  };

  public Iterator<IPerson> getReaders() {
    return _readers.values().iterator();
  };

  public int addPage(IPage page) {
    _pages.put(_pages.size(), page);
    return _pages.size();
  };

  public IPage deletePage(int pageNo) {
    return _pages.remove(pageNo);
  };

  public int pageCount() {
    return _pages.size();
  };

  public IPage getAveragePage() throws NoSuchElementException {
    if (0 == pageCount()) {
      throw new NoSuchElementException();
    }
    int averageWidth = 0;
    int averageHeight = 0;
    int colorPageCount = 0;
    int blackPageCount = 0;
    int totalPages = pageCount();
    PageColor averageColor = PageColor.MIXT;

    for (IPage p : _pages.values()) {
      averageWidth = (averageHeight + p.getWidth()) / totalPages;
      averageHeight = (averageHeight + p.getHeight()) / totalPages;
      PageColor currPageColor = p.getColor();
      if (PageColor.COLOR == currPageColor) {
        colorPageCount++;
      }
      if (PageColor.BLACK == currPageColor) {
        blackPageCount++;
      }
    }

    if (0 == colorPageCount) {
      averageColor = PageColor.BLACK;
    }
    if (0 == blackPageCount) {
      averageColor = PageColor.COLOR;
    }

    return new Page(averageWidth, averageHeight, averageColor);
  };

  public Label getLabel() {
    return Book._label;
  }

  public String toString() {
    return String.format(
        "Book: {id: %s," +
            "title: %s," +
            "authors: " + _authors.toString() +
            "}",
        _id,
        _title);
  }
}
