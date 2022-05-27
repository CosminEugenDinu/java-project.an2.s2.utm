import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.naming.LimitExceededException;

import app.Author;
import app.Person;
import constants.AuthorTitle;
import constants.BookTitles;
import constants.Label;
import constants.PageColor;
import constants.PersNames;
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
    String[] _personsStr = new String[1 + PersNames.list.length];
    _personsStr[0] = String.join(separator, PersNames.header);
    int i = 1;
    for (String name : PersNames.list) {
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
    int BOOK_MAX_PAGE_COUNT = 100;
    ArrayList<String> _pagesStr = new ArrayList<String>();
    int pageId = 1;

    String[] header = { "id", "book_id", "page_no", "width", "height", "color" };
    _pagesStr.add(String.join(separator, header));

    for (int i = 1; i < bookTitles.length; i++) {
      String bookId = String.valueOf(i);
      int pageCount = randInt(1, BOOK_MAX_PAGE_COUNT);
      int dimensRand = randInt(1, 2);
      String width = String.valueOf(210 / dimensRand);
      String height = String.valueOf(297 / dimensRand);
      PageColor color = randInt(0, 1) == 0 ? PageColor.BLACK : PageColor.COLOR;

      for (int j = 1; j <= pageCount; j++) {
        String pageIdStr = String.valueOf(pageId++);
        String pageNo = String.valueOf(j);
        String[] pageData = { pageIdStr, bookId, pageNo, width, height, color.toString() };
        String pageEntry = String.join(separator, pageData);
        _pagesStr.add(pageEntry);
      }
    }
    String[] pagesData = new String[_pagesStr.size()];
    return _pagesStr.toArray(pagesData);
  }

  public static String[] mockContributorsData(String[] bookTitles, String[] personNames) {
    int BOOK_MAX_AUTHOR_COUNT = 2;
    // int BOOK_MAX_READER_COUNT = personNames.length;
    int BOOK_MAX_READER_COUNT = 2;

    ArrayList<String> _contribsStr = new ArrayList<String>();
    int contribId = 1;

    String[] header = { "id", "book_id", "pers_id", "label", "author_title" };
    _contribsStr.add(String.join(separator, header));

    String[] labels = { Label.AUTHOR.toString(), Label.READER.toString() };
    String[] authorTitles = {
        AuthorTitle.ASIST_UNIV.toString(),
        AuthorTitle.CERCETATOR.toString(),
        AuthorTitle.ASIST_UNIV.toString(),
        AuthorTitle.LECTOR_UNIV.toString(),
        AuthorTitle.CONF_UNIV.toString(),
        AuthorTitle.PROF_UNIV.toString(),
        AuthorTitle.DOCTOR.toString(),
        AuthorTitle.STUDENT.toString(),
        AuthorTitle.SCRIITOR.toString(),
        AuthorTitle.POET.toString()
    };

    for (int i = 1; i <= bookTitles.length; i++) {
      String bookId = String.valueOf(i);
      int authorCount = randInt(1, BOOK_MAX_AUTHOR_COUNT);
      int readerCount = randInt(1, BOOK_MAX_READER_COUNT);

      int[] randAuthorIds = randUniques(1, personNames.length, BOOK_MAX_AUTHOR_COUNT);
      int[] randReaderIds = randUniques(1, personNames.length, BOOK_MAX_READER_COUNT);

      for (int j = 1; j <= authorCount; j++) {
        String contribIdStr = String.valueOf(contribId++);
        String persId = String.valueOf(randAuthorIds[j - 1]);
        String label = Label.AUTHOR.toString();
        int randAuthorTitleIndex = randInt(0, authorTitles.length - 1);
        String authorTitle = authorTitles[randAuthorTitleIndex];
        String[] data = { contribIdStr, bookId, persId, label, authorTitle };
        _contribsStr.add(String.join(separator, data));
      }

      for (int j = 1; j <= readerCount; j++) {
        String contribIdStr = String.valueOf(contribId++);
        String persId = String.valueOf(randReaderIds[j - 1]);
        String label = Label.READER.toString();
        String authorTitle = Label.NULL.toString();
        String[] data = { contribIdStr, bookId, persId, label, authorTitle };
        _contribsStr.add(String.join(separator, data));
      }
    }
    String[] data = new String[_contribsStr.size()];
    return _contribsStr.toArray(data);
  }

  public static int[] randUniques(int from, int to, int len) {
    int[] randInts = new int[len];
    ArrayList<Integer> list = new ArrayList<>();
    for (int i = from; i <= to; i++) {
      list.add(i);
    }
    Collections.shuffle(list);
    for (int j = 0; j < len; j++) {
      randInts[j] = list.get(j);
    }
    return randInts;
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

    // data = Tools.mockContributorsData(BookTitles.list, PersNames.list);
    // writtenFile = Storage.writeFile("contrib.csv", data);
    // System.out.println(writtenFile);
    // readData = Storage.readFile("contrib.csv");
    // System.out.println(String.join("\n", readData));
  }
}
