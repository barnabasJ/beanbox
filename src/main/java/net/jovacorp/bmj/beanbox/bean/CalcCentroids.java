package net.jovacorp.bmj.beanbox.bean;

import net.jovacorp.bmj.beanbox.Coordinate;
import net.jovacorp.bmj.beanbox.Coordinates;
import net.jovacorp.bmj.beanbox.event.Event;

import javax.media.jai.PlanarImage;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

public class CalcCentroids
    extends AbstractProcessBean<PlanarImage, Event<PlanarImage>, Coordinates> {
  private HashMap<Coordinate, Boolean> general;
  private LinkedList<ArrayList<Coordinate>> figures;

  public CalcCentroids() {
    super();
  }

  @Override
  protected Coordinates processData(PlanarImage inputData) {
    general = new HashMap<>();
    figures = new LinkedList<>();

    BufferedImage bi = inputData.getAsBufferedImage();

    for (int x = 0; x < bi.getWidth(); x++) {
      for (int y = 0; y < bi.getHeight(); y++) {
        int p = bi.getRaster().getSample(x, y, 0);
        if (p == 255 && general.containsKey(new Coordinate(x, y)) == false) {
          getNextFigure(
              bi, x,
              y); // if there is a not visited white pixel, save all pixels belonging to the same
          // figure
        }
      }
    }

    return new Coordinates(calculateCentroids(inputData));
  }

  private void getNextFigure(BufferedImage img, int x, int y) {
    ArrayList<Coordinate> figure = new ArrayList<>();
    general.put(new Coordinate(x, y), true);
    figure.add(new Coordinate(x, y));

    addConnectedComponents(img, figure, x, y);

    figures.add(figure);
  }

  private void addConnectedComponents(
      BufferedImage img, ArrayList<Coordinate> figure, int x, int y) {
    if (x - 1 >= 0
        && general.containsKey((new Coordinate(x - 1, y))) == false
        && img.getRaster().getSample(x - 1, y, 0) == 255) {
      general.put(new Coordinate(x - 1, y), true);
      figure.add(new Coordinate(x - 1, y));
      addConnectedComponents(img, figure, x - 1, y);
    }
    if (x + 1 < img.getWidth()
        && general.containsKey((new Coordinate(x + 1, y))) == false
        && img.getRaster().getSample(x + 1, y, 0) == 255) {
      general.put(new Coordinate(x + 1, y), true);
      figure.add(new Coordinate(x + 1, y));
      addConnectedComponents(img, figure, x + 1, y);
    }
    if (y - 1 >= 0
        && general.containsKey((new Coordinate(x, y - 1))) == false
        && img.getRaster().getSample(x, y - 1, 0) == 255) {
      general.put(new Coordinate(x, y - 1), true);
      figure.add(new Coordinate(x, y - 1));
      addConnectedComponents(img, figure, x, y - 1);
    }
    if (y + 1 < img.getHeight()
        && general.containsKey((new Coordinate(x, y + 1))) == false
        && img.getRaster().getSample(x, y + 1, 0) == 255) {
      general.put(new Coordinate(x, y + 1), true);
      figure.add(new Coordinate(x, y + 1));
      addConnectedComponents(img, figure, x, y + 1);
    }
  }

  private ArrayList<Coordinate> calculateCentroids(PlanarImage inputData) {
    PlanarImage originalImage = inputData;
    ArrayList<Coordinate> centroids = new ArrayList<>();

    for (ArrayList<Coordinate> figure : figures) {
      LinkedList<Integer> xValues = new LinkedList<>();
      LinkedList<Integer> yValues = new LinkedList<>();

      for (Coordinate c : figure) {
        xValues.add(c.x);
        yValues.add(c.y);
      }

      Collections.sort(xValues);
      Collections.sort(yValues);

      int xMedian = xValues.get(xValues.size() / 2);
      int yMedian = yValues.get(yValues.size() / 2);

      centroids.add(
          new Coordinate(
              xMedian + (Integer) originalImage.getProperty("offsetX"),
              yMedian + (Integer) originalImage.getProperty("offsetY")));
    }

    return centroids;
  }
}
