package interfaces;

import java.util.Iterator;

import constants.AuthorTitle;

public interface IBook extends ILabel {
  public int getId();

  public int setId(int id);

  public void setTitle(String title);

  public String getTitle();

  public int addAuthor(IAuthor author, AuthorTitle authorTitle);

  public int addBookAuthors(IBookAuthor[] bookAuthors);

  public IAuthor[] getAuthors();

  public int addReader(IPerson reader);

  public Iterator<IPerson> getReaders();

  public int addPage(IPage page);

  public IPage deletePage(int pageNo);

  public int pageCount();

  public IPage getAveragePage();

}
