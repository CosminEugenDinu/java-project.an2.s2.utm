package app;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import interfaces.IAuthor;
import interfaces.IBook;
import interfaces.IBookAuthor;
import interfaces.IPerson;
import constants.Label;
import constants.AuthorTitle;

public class Book implements IBook {
  private static final Label _label = Label.BOOK;
  private String _title;
  private ArrayList<IBookAuthor> _authors = new ArrayList<IBookAuthor>();
  private HashMap<String, IPerson> _readers = new HashMap<>();
  // private HashMap<Integer, IPage> _pages = new HashMap<>();

  Book(String title) {
    _title = title;
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

  public IAuthor[] getAuthors() {
    IAuthor[] authors = new IAuthor[_authors.size()];
    int i = 0;
    for (IAuthor a : authors) {
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

  // public int addPage(IPage page);

  // public IPage deletePage(int pageNo);

  // public int pageCount();

  // public IPage getAveragePage();


  public Label getLabel() {
    return Book._label;
  }
}
