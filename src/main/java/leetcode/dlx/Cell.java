package leetcode.dlx;

public class Cell<T> extends AbstractCell {

  T name;
  ColumnHeader columnHeader;

  @Override protected String getDescription() {
    if (columnHeader == null) {
      return "cell " + name;
    }
    return "cell " + columnHeader.index + ", " + name;
  }

}