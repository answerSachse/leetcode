package leetcode.dlx;

public class Head extends AbstractCell {

  Head() {
    left = this;
    right = this;
  }

  @Override protected String getDescription() {
    return "head";
  }


}
