package app;

import constants.Label;
import interfaces.IAuthor;
import interfaces.IPerson;

public class Author implements IAuthor {
  private static final Label _label = Label.AUTHOR;

  private IPerson _person;

  public Author(IPerson person) {
    _person = person;
  }

  public IPerson setPerson(IPerson person) {
    _person = new Person(person.getEmail(), person.getName());
    return _person;
  }

  public IPerson getPerson() {
    return _person;
  }

  public IPerson updatePerson(IPerson person) {
    _person.setEmail(person.getEmail());
    _person.setName(person.getName());
    return _person;
  }

  public Label getLabel() {
    return Author._label;
  }
}
