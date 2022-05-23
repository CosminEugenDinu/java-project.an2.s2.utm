package constants;

public enum AuthorTitle {
  CERCETATOR("cercetător"),
  ASIST_UNIV("asistent universitar"),
  LECTOR_UNIV("lector universitar"),
  CONF_UNIV("conferențiar universitar"),
  PROF_UNIV("profesor universitar"),
  DOCTOR("doctor"),
  STUDENT("student"),
  SCRIITOR("scriitor"),
  POET("poet");

  private final String _title;

  AuthorTitle(final String title) {
    _title = title;
  }

  public String toString() {
    return _title;
  }
}
