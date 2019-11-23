package net.jovacorp.bmj.beanbox;

import lombok.Data;

@Data
public class Coordinate {
  public final int x;
  public final int y;

  @Override
  public String toString() {
    return "[" + x + "," + y + "]";
  }
}
