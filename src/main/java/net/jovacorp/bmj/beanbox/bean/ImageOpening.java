package net.jovacorp.bmj.beanbox.bean;

import lombok.Getter;
import net.jovacorp.bmj.beanbox.event.ImageEvent;

import javax.media.jai.KernelJAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.RenderedOp;
import javax.media.jai.operator.DilateDescriptor;
import javax.media.jai.operator.ErodeDescriptor;

public class ImageOpening extends AbstractProcessBean<PlanarImage, ImageEvent, PlanarImage> {

  private float[] kernelMatrix;
  private int kernelDimensionX;
  private int kernelDimensionY;
  @Getter private int executionAmount;

  public ImageOpening() {
    super();

    kernelMatrix =
        new float[] {
          0, 1, 0,
          1, 1, 1,
          0, 1, 0
        };

    kernelDimensionX = 3;
    kernelDimensionY = 3;
    executionAmount = 4;
  }

  public void setExecutionAmount(int executionAmount) {
    this.executionAmount = executionAmount;
    process();
  }

  @Override
  protected PlanarImage processData(PlanarImage inputData) {
    KernelJAI kernel = new KernelJAI(kernelDimensionX, kernelDimensionY, kernelMatrix);

    // erode
    RenderedOp outputData = ErodeDescriptor.create(inputData, kernel, null);
    for (int i = 0; i < executionAmount - 1; i++) {
      outputData = ErodeDescriptor.create(outputData, kernel, null);
    }

    // dilate
    for (int i = 0; i < executionAmount; i++) {
      outputData = DilateDescriptor.create(outputData, kernel, null);
    }

    outputData.setProperty("offsetX", inputData.getProperty("offsetX"));
    outputData.setProperty("offsetY", inputData.getProperty("offsetY"));
    return outputData;
  }
}
