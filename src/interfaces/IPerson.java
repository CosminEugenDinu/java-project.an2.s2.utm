package interfaces;

public interface IPerson {
  public void setEmail(String email);

  public void setName(String name);

  public void setPhone(int phone);

  public String getName();

  public String getEmail();

  public int getPhone();

  public int setBornDate(int bornYear);

  public int getAge();

}