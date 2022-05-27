package interfaces;

public interface IPerson {
  public int getId();

  public int setId(int id);

  public void setEmail(String email);

  public void setName(String name);

  public String getName();

  public String getEmail();

  public int setBornDate(int bornYear);

  public int getAge();

}