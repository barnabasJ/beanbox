package net.jovacorp.bmj.beanbox.bean;

import net.jovacorp.bmj.beanbox.event.Event;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import java.awt.image.renderable.ParameterBlock;

public class ImageThreshold
    extends AbstractProcessBean<PlanarImage, Event<PlanarImage>, PlanarImage> {

  private double low;
  private double high;
  private double map;

  public ImageThreshold() {
    super();

    low = 0;
    high = 35;
    map = 255;
  }

  public double getLow() {
    return low;
  }

  public void setLow(double low) {
    this.low = low;
    process();
  }

  public double getHigh() {
    return high;
  }

  public void setHigh(double high) {
    this.high = high;
    process();
  }

  public double getMap() {
    return map;
  }

  public void setMap(double map) {
    this.map = map;
    process();
  }

  @Override
  protected PlanarImage processData(PlanarImage inputData) {
    ParameterBlock pb = new ParameterBlock();
    pb.addSource(inputData);
    pb.add(new double[] {low});
    pb.add(new double[] {high});
    pb.add(new double[] {map});

    PlanarImage outputData = JAI.create("threshold", pb);
    outputData.setProperty("offsetX", inputData.getProperty("offsetX"));
    outputData.setProperty("offsetY", inputData.getProperty("offsetY"));
    return outputData;
  }
}
