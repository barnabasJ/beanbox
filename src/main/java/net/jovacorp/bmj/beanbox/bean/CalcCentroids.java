package net.jovacorp.bmj.beanbox.bean;

import net.jovacorp.bmj.beanbox.Coordinate;
import net.jovacorp.bmj.beanbox.event.Event;

import javax.media.jai.PlanarImage;
import java.util.List;

public class CalcCentroids extends AbstractProcessBean<PlanarImage, Event<PlanarImage>, List<Coordinate>> {
  @Override
  protected List<Coordinate> processData(PlanarImage inputData) {
    return null;
  }
}
