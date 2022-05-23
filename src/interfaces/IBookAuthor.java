package interfaces;

import constants.AuthorTitle;

public interface IBookAuthor extends IAuthor {
  public void setAuthorTitle(AuthorTitle authorTitle);

  public AuthorTitle getAuthorTitle();
}
