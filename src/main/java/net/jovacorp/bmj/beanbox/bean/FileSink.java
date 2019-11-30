package net.jovacorp.bmj.beanbox.bean;

import lombok.Getter;
import net.jovacorp.bmj.beanbox.Coordinate;
import net.jovacorp.bmj.beanbox.Coordinates;
import net.jovacorp.bmj.beanbox.event.Event;
import net.jovacorp.bmj.beanbox.listener.Listener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class FileSink implements Listener<Event<Coordinates>> {
  @Getter private String resultFilePath;
  private List<Coordinate> deviations;

  public FileSink() {
    super();
    this.resultFilePath = "";
  }

  public void setResultFilePath(String resultFilePath) {
    this.resultFilePath = resultFilePath;
    if (deviations != null) {
      process(deviations);
    }
  }

  public void process(List<Coordinate> deviations) {
    File resultFile = new File(resultFilePath);
    if (!resultFile.exists()) {
      try {
        resultFile.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    if (resultFile.exists()) {
      try (PrintWriter pw = new PrintWriter(resultFile)) {
        for (Coordinate deviation : deviations) {
          pw.println(deviation);
        }
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    } else {
      throw new IllegalStateException("Couldn't create result file");
    }
  }

  @Override
  public void sourceChanged(Event<Coordinates> coordinatesEvent) {
    deviations = coordinatesEvent.getEventData().getCoordinates();
    process(deviations);
  }
}
