import app.Person;
import interfaces.IPerson;

class MainApp {
  public static void main(String[] args) {
    System.out.println("Hello, World!");
    IPerson p = new Person("cos@email.com");
    System.out.println(p);
  }
}