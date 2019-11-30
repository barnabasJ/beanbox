package net.jovacorp.bmj.beanbox.bean;

import lombok.Getter;
import net.jovacorp.bmj.beanbox.Coordinate;
import net.jovacorp.bmj.beanbox.Coordinates;
import net.jovacorp.bmj.beanbox.event.Event;
import net.jovacorp.bmj.beanbox.listener.Listener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class FileSink extends Label implements Listener<Event<Coordinates>> {
  @Getter private File resultFile;
  private List<Coordinate> deviations;

  public FileSink() {
    addMouseListener(
        new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            openFileChooser();
            setFileText();
          }
        });
  }

  private void openFileChooser() {
    JFileChooser fileChooser = new JFileChooser();

    int returnValue = fileChooser.showOpenDialog(this);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      resultFile = fileChooser.getSelectedFile();
    }
  }

  private void setFileText() {
    if (resultFile == null) {
      setText("Click to choose image");
    } else {
      setText(resultFile.getAbsolutePath());
    }
  }

  public void process(List<Coordinate> deviations) {
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
