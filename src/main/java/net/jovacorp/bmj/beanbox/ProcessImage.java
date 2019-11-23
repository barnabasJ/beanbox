package net.jovacorp.bmj.beanbox;

import lombok.Data;

import javax.media.jai.PlanarImage;
import java.util.ArrayList;

@Data
public class ProcessImage {
  private ArrayList<Coordinate> centroids;
  private PlanarImage originalImage;
  private PlanarImage editedImage;
  private double faultTolerance;
}
