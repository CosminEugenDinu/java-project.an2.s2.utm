package app;

import java.util.Calendar;

import interfaces.IPerson;

public class Person implements IPerson {
  private String _email;
  private String _name = "anonymous";
  private int _phone = 0;
  private int _bornYear = 0;

  public Person(String email, String name, int phone) {
    _email = email;
    _name = name;
    _phone = phone;
  }

  public Person(String email, String name) {
    _email = email;
    _name = name;
  }

  public Person(String email) {
    _email = email;
  }

  private static String validateEmail(String email) {
    if (0 == email.length()) {
      throw new IllegalArgumentException("email cannot be empty");
    }
    return email;
  };

  public void setEmail(String email) throws IllegalArgumentException {
    Person.validateEmail(email);
    _email = email;
  }

  public void setName(String name) {
    _email = name;
  }

  public void setPhone(int phone) {
    _phone = phone;
  }

  public String getEmail() {
    return _email;
  }

  public String getName() {
    return _name;
  }

  public int getPhone() {
    return _phone;
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
    System.out.println("Person:\nemail,name,phone");
    return String.format("%s,%s,%s", getEmail(), getName(), getPhone());
  }
}
