package net.jovacorp.bmj.beanbox.bean;

import net.jovacorp.bmj.beanbox.Coordinate;
import net.jovacorp.bmj.beanbox.listener.Listener;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class FileSink extends Label implements Listener<List<Coordinate>> {
  private String pathToFile;

  @Override
  public void sourceChanged(List<Coordinate> deviations) {
    try (PrintWriter pw = new PrintWriter(pathToFile)) {
      for (Coordinate deviation : deviations) {
        pw.println(deviation);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
