package interfaces;

import constants.Label;

public interface IAuthor extends ILabel {
  public static final Label LABEL = Label.AUTHOR;

  public IPerson setPerson(IPerson person);

  public IPerson updatePerson(IPerson person);

  public IPerson getPerson();
}
