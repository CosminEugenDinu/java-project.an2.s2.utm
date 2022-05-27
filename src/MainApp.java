import java.io.IOException;

import interfaces.IBook;
import operations.BookOps;
import operations.PersonOps;

class MainApp {
  public static void main(String[] args) throws IOException {
    Tools.writeMockData();
    PersonOps persOps = new PersonOps();
    persOps.uploadData();
    System.out.println("-".repeat(50));
    System.out.println(persOps.getById(30));
    System.out.println("-".repeat(50));
    BookOps bo = new BookOps();
    bo.uploadData();
    IBook b1 = bo.getById(3);
    System.out.println(b1);
    System.out.println("-".repeat(50));
  }
}
