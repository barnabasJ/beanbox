package net.jovacorp.bmj.beanbox.bean;

import lombok.Getter;
import net.jovacorp.bmj.beanbox.event.ImageEvent;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.operator.MedianFilterDescriptor;
import javax.media.jai.operator.MedianFilterShape;
import java.awt.image.renderable.ParameterBlock;

public class ImageMedian extends AbstractProcessBean<PlanarImage, ImageEvent, PlanarImage> {

  private MedianFilterShape medianFilterShape;
  @Getter private int maskSize;

  public ImageMedian() {
    super();

    medianFilterShape = MedianFilterDescriptor.MEDIAN_MASK_SQUARE;
    maskSize = 4;
  }

  public void setMaskSize(int maskSize) {
    this.maskSize = maskSize;
    process();
  }

  @Override
  protected PlanarImage processData(PlanarImage inputData) {
    ParameterBlock pb = new ParameterBlock();
    pb.addSource(inputData);
    pb.add(medianFilterShape);
    pb.add(maskSize);

    PlanarImage outputData = JAI.create("MedianFilter", pb);
    outputData.setProperty("offsetX", inputData.getProperty("offsetX"));
    outputData.setProperty("offsetY", inputData.getProperty("offsetY"));
    return outputData;
  }
}
