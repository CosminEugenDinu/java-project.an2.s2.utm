package app;

import interfaces.IAuthor;
import interfaces.IPerson;

public abstract class Author implements IAuthor {
  private IPerson _person;

  public Author(IPerson person) {
    _person = person;
  }

  public IPerson setPerson(IPerson person) {
    _person = new Person(person.getEmail(), person.getName(), person.getPhone());
    return _person;
  }

  public IPerson getPerson() {
    return _person;
  }

  public IPerson updatePerson(IPerson person) {
    _person.setEmail(person.getEmail());
    _person.setName(person.getName());
    _person.setPhone(person.getPhone());
    return _person;
  }
}
