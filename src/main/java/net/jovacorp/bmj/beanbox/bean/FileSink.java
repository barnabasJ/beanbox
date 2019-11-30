package net.jovacorp.bmj.beanbox.bean;

import lombok.Getter;
import net.jovacorp.bmj.beanbox.Coordinate;
import net.jovacorp.bmj.beanbox.listener.Listener;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class FileSink extends Label implements Listener<List<Coordinate>> {
  @Getter private String pathToFile;
  private List<Coordinate> deviations;

  public FileSink() {
    super();
    setText("FileSink");
  }

  public void setPathToFile(String pathToFile) {
    this.pathToFile = pathToFile;
    if (deviations != null) {
      sourceChanged(deviations);
    }
  }

  @Override
  public void sourceChanged(List<Coordinate> deviations) {
    this.deviations = deviations;
    try (PrintWriter pw = new PrintWriter(pathToFile)) {
      for (Coordinate deviation : deviations) {
        pw.println(deviation);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
