package app;

import java.util.Calendar;

import interfaces.IPerson;

public class Person implements IPerson {
  private int _id = 0;
  private String _email;
  private String _name = "anonymous";
  private int _bornYear = 0;

  private static String validateEmail(String email) {
    if (0 == email.length()) {
      throw new IllegalArgumentException("email cannot be empty");
    }
    return email;
  };

  public Person(String email, String name) {
    _email = email;
    _name = name;
  }

  public Person(String email) {
    _email = email;
  }

  public int setId(int id) {
    _id = id;
    return id;
  }

  public int getId() {
    return _id;
  }

  public void setEmail(String email) throws IllegalArgumentException {
    Person.validateEmail(email);
    _email = email;
  }

  public void setName(String name) {
    _email = name;
  }

  public String getEmail() {
    return _email;
  }

  public String getName() {
    return _name;
  }

  public int setBornDate(int bornYear) {
    _bornYear = bornYear;
    return _bornYear;
  }

  public int getAge() {
    int thisYear = Calendar.getInstance().get(Calendar.YEAR);
    return thisYear - _bornYear;
  }

  public String toString() {
    return String.format(
        "Person: {id: %s, email: %s, name: %s, born: %s}",
        _id, _email, _name, _bornYear);
  }
}
