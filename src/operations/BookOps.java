package operations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.naming.NameNotFoundException;

import constants.AuthorTitle;
import constants.BookDataField;
import constants.ContribDataField;
import constants.PageDataField;
import constants.StorageCt;
import interfaces.IAuthor;
import interfaces.IBook;
import interfaces.IBookAuthor;
import interfaces.IPerson;
import service.Storage;
import app.Author;
import app.Book;
import app.BookAuthor;

class Tuple<X, Y> {
  public final X x;
  public final Y y;

  public Tuple(X x, Y y) {
    this.x = x;
    this.y = y;
  }
}

public class BookOps {
  private static final String CSV_SEP = StorageCt.CSV_SEP.toString();
  private static ArrayList<String> _booksRawData;
  private static ArrayList<String> _contribsRawData;
  private static ArrayList<String> _pagesRawData;
  private static PersonOps _persOps;

  private static HashMap<Integer, String[]> _bookDataById = new HashMap<>();
  private static HashMap<Integer, String[]> _contribDataById = new HashMap<>();
  private static HashMap<Integer, ArrayList<Integer>> _contribIdsByBookId = new HashMap<>();
  private static HashMap<Integer, ArrayList<Integer>> _contribIdsByPersId = new HashMap<>();
  private static HashMap<Integer, String[]> _pageDataById = new HashMap<>();
  private static HashMap<Integer, ArrayList<Integer>> _pageIdsByBookId = new HashMap<>();

  private static HashMap<Integer, IBook> _books = new HashMap<>();

  private static void _dataToBooks() {

    for (int bookId : _bookDataById.keySet()) {
      String[] bookSpreadData = _bookDataById.get(bookId);
      String bookTitle = bookSpreadData[BookDataField.TITLE.ordinal()];
      IBook book = new Book(bookTitle);
      book.setId(bookId);
      book.addBookAuthors(getAuthorsByBookId(bookId));

      _books.put(bookId, book);
    }
  }

  private static void _parseBooksRawData() {
    for (int i = 1; i < _booksRawData.size(); i++) {
      String bookRawData = _booksRawData.get(i);
      String[] bookSpreadData = bookRawData.split(CSV_SEP);

      String rawBookId = bookSpreadData[BookDataField.ID.ordinal()];

      int bookId = Integer.parseUnsignedInt(rawBookId);

      _bookDataById.put(bookId, bookSpreadData);
    }
  }

  private static void _parseContribsRawData() {
    for (int i = 1; i < _contribsRawData.size(); i++) {
      String contribRawData = _contribsRawData.get(i);
      String[] contribSpreadData = contribRawData.split(CSV_SEP);

      String rawContribId = contribSpreadData[ContribDataField.ID.ordinal()];
      String rawBookId = contribSpreadData[ContribDataField.BOOK_ID.ordinal()];
      String rawPersId = contribSpreadData[ContribDataField.PERS_ID.ordinal()];

      int contribId = Integer.parseUnsignedInt(rawContribId);
      int bookId = Integer.parseUnsignedInt(rawBookId);
      int persId = Integer.parseUnsignedInt(rawPersId);

      _contribDataById.put(contribId, contribSpreadData);

      if (_contribIdsByBookId.containsKey(bookId)) {
        _contribIdsByBookId.get(bookId).add(contribId);
      } else {
        ArrayList<Integer> contribIds = new ArrayList<Integer>();
        contribIds.add(contribId);
        _contribIdsByBookId.put(bookId, contribIds);
      }

      if (_contribIdsByPersId.containsKey(persId)) {
        _contribIdsByPersId.get(persId).add(contribId);
      } else {
        ArrayList<Integer> contribIds = new ArrayList<Integer>();
        contribIds.add(contribId);
        _contribIdsByPersId.put(persId, contribIds);
      }
    }
  }

  private static void _parsePagesRawData() {
    for (int i = 1; i < _pagesRawData.size(); i++) {
      String pageRawData = _pagesRawData.get(i);
      String[] pageSpreadData = pageRawData.split(CSV_SEP);

      String rawPageId = pageSpreadData[PageDataField.ID.ordinal()];
      String rawBookId = pageSpreadData[PageDataField.BOOK_ID.ordinal()];

      int pageId = Integer.parseUnsignedInt(rawPageId);
      int bookId = Integer.parseUnsignedInt(rawBookId);

      _pageDataById.put(pageId, pageSpreadData);

      if (_pageIdsByBookId.containsKey(bookId)) {
        _pageIdsByBookId.get(bookId).add(pageId);
      } else {
        ArrayList<Integer> pageIds = new ArrayList<Integer>();
        pageIds.add(pageId);
        _pageIdsByBookId.put(bookId, pageIds);
      }
    }
  }

  private static void _uploadData() throws IOException {
    _booksRawData = Storage.readFile(StorageCt.BOOK_FILE.toString());
    _contribsRawData = Storage.readFile(StorageCt.CONTRIB_FILE.toString());
    _pagesRawData = Storage.readFile(StorageCt.PAGE_FILE.toString());
    _persOps = new PersonOps();
    _persOps.uploadData();
    _parseBooksRawData();
    _parseContribsRawData();
    _parsePagesRawData();
    _dataToBooks();
  }

  public static IBookAuthor[] getAuthorsByBookId(int bookId) {
    ArrayList<Integer> contribIds = _contribIdsByBookId.get(bookId);
    int bookAuthorCount = contribIds.size();
    IBookAuthor[] bookAuthors = new IBookAuthor[bookAuthorCount];
    int index = 0;
    for (int contribId : contribIds) {
      String[] contribSpreadData = _contribDataById.get(contribId);
      IPerson person = _persOps.getById(contribId);
      IAuthor author = new Author(person);
      IBookAuthor bookAuthor = new BookAuthor(author);
      String authorTitle = contribSpreadData[ContribDataField.AUTHOR_TITLE.ordinal()];
      bookAuthor.setAuthorTitle(authorTitle);
      bookAuthors[index++] = bookAuthor;
    }
    return bookAuthors;
  }

  public BookOps uploadData() {
    try {
      _uploadData();
    } catch (IOException e) {
      System.out.println(e);
    }
    return this;
  }

  public IBook getById(int id) {
    return _books.get(id);
  }

  public String toString() {
    return _booksRawData.toString();
  }
}
