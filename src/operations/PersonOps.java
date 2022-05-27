package operations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import app.Person;
import constants.PersDataField;
import constants.StorageCt;
import interfaces.IPerson;
import service.Storage;

public class PersonOps {
  private static final String CSV_SEP = StorageCt.CSV_SEP.toString();
  private static ArrayList<String> _personsData;
  private static HashMap<Integer, IPerson> _persons = new HashMap<>();

  private static IPerson _personDataToObject(String personData) {
    String[] spreadData = personData.split(CSV_SEP);
    int id = Integer.parseUnsignedInt(spreadData[PersDataField.ID.ordinal()]);
    String email = spreadData[PersDataField.EMAIL.ordinal()];
    String name = spreadData[PersDataField.NAME.ordinal()];
    int born = Integer.parseUnsignedInt(spreadData[PersDataField.BORN.ordinal()]);
    IPerson pers = new Person(email, name);
    pers.setBornDate(born);
    pers.setId(id);
    return pers;
  }

  public PersonOps uploadData() {
    try {
      _personsData = Storage.readFile(StorageCt.PERS_FILE.toString());
      for (int i = 1; i < _personsData.size(); i++) {
        IPerson person = _personDataToObject(_personsData.get(i));
        _persons.put(person.getId(), person);
      }
    } catch (IOException e) {
      System.out.println(e);
    }
    return this;
  }

  public IPerson getById(int id) {
    return _persons.get(id);
  }

  public IPerson[] getAll() {
    IPerson[] persons = new IPerson[_personsData.size() - 1];
    int i = 0;
    for (IPerson pers : _persons.values()) {
      persons[i++] = pers;
    }
    return persons;
  }

  public String toString() {
    return _personsData.toString();
  }
}
