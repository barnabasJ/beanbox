package net.jovacorp.bmj.beanbox.bean;

import net.jovacorp.bmj.beanbox.Coordinate;
import net.jovacorp.bmj.beanbox.event.ImageEvent;

import javax.media.jai.PlanarImage;
import java.util.List;

public class CalcCentroids extends AbstractProcessBean<PlanarImage, ImageEvent, List<Coordinate>> {
  @Override
  protected List<Coordinate> processData(PlanarImage inputData) {
    return null;
  }
}
