import java.io.IOException;
import java.util.ArrayList;

import constants.BookTitles;
import constants.PageColor;
import constants.PersonNames;
import service.Storage;

public class Tools {
  public static String separator = ",";

  public static String nameToEmail(String name) {
    return name.toLowerCase().replace(" ", ".").concat("@email.ro");
  }

  public static int randInt(int min, int max) {
    return (int) Math.floor((Math.random() * (max - min + 1) + min));
  }

  public static String nameToPersonStr(String id, String name) {
    String email = Tools.nameToEmail(name);
    int minY = 1986;
    int maxY = 2000;
    String bornYear = String.valueOf(randInt(minY, maxY));
    return String.join(separator, id, email, name, bornYear);
  }

  public static String[] mockPersonsData() {
    String[] _personsStr = new String[1 + PersonNames.list.length];
    _personsStr[0] = String.join(separator, PersonNames.header);
    int i = 1;
    for (String name : PersonNames.list) {
      String id = String.valueOf(i);
      _personsStr[i++] = Tools.nameToPersonStr(id, name);
    }
    return _personsStr;
  }

  public static String[] mockBooksData() {
    String[] _booksStr = new String[1 + BookTitles.list.length];
    _booksStr[0] = String.join(separator, BookTitles.header);
    int i = 1;
    for (String title : BookTitles.list) {
      String id = String.valueOf(i);
      _booksStr[i++] = String.join(separator, id, title);
    }
    return _booksStr;
  }

  public static String[] mockPagesData(String[] bookTitles) {
    ArrayList<String> _pagesStr = new ArrayList<String>();
    String[] header = { "id", "book_id", "page_no", "width", "height", "color" };
    _pagesStr.add(String.join(separator, header));
    int BOOK_MAX_PAGE_COUNT = 100;
    int pageId = 1;
    for (int i = 1; i < bookTitles.length; i++) {
      String bookId = String.valueOf(i);
      int pageCount = randInt(1, BOOK_MAX_PAGE_COUNT);
      int dimensRand = randInt(1, 2);
      String width = String.valueOf(210 / dimensRand);
      String height = String.valueOf(297 / dimensRand);
      PageColor color = randInt(0, 1) == 0 ? PageColor.BLACK : PageColor.COLOR;
      for (int j = 1; j <= pageCount; j++) {
        String pageIdStr = String.valueOf(++pageId);
        String pageNo = String.valueOf(j);
        String[] pageData = { pageIdStr, bookId, pageNo, width, height, color.toString() };
        String pageEntry = String.join(separator, pageData);
        _pagesStr.add(pageEntry);
      }
    }
    String[] pagesData = new String[_pagesStr.size()];
    // pagesData = _pagesStr.toArray(pagesData);
    return _pagesStr.toArray(pagesData);
  }

  public static void writeMockData() throws IOException {
    String[] data;
    String writtenFile;
    ArrayList<String> readData;

    // data = Tools.mockPersonsData();
    // writtenFile = Storage.writeFile("pers.csv", data);
    // System.out.println(writtenFile);
    // readData = Storage.readFile("pers.csv");
    // System.out.println(String.join("\n", readData));

    // data = Tools.mockBooksData();
    // writtenFile = Storage.writeFile("book.csv", data);
    // System.out.println(writtenFile);
    // readData = Storage.readFile("book.csv");
    // System.out.println(String.join("\n", readData));

    // data = Tools.mockPagesData(BookTitles.list);
    // writtenFile = Storage.writeFile("page.csv", data);
    // System.out.println(writtenFile);
    // readData = Storage.readFile("page.csv");
    // System.out.println(String.join("\n", readData));
  }
}
