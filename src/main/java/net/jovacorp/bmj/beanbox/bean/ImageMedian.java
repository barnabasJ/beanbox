package net.jovacorp.bmj.beanbox.bean;

import javax.media.jai.JAI;
import javax.media.jai.operator.MedianFilterDescriptor;
import javax.media.jai.operator.MedianFilterShape;
import java.awt.image.renderable.ParameterBlock;

public class ImageMedian extends AbstractImageProcessBean {

    private MedianFilterShape medianFilterShape;
    private int maskSize;

    public ImageMedian() {
        super();

        medianFilterShape = MedianFilterDescriptor.MEDIAN_MASK_SQUARE;
        maskSize = 4;
    }

    public int getMaskSize() {
        return maskSize;
    }

    public void setMaskSize(int maskSize) {
        this.maskSize = maskSize;
        process();
    }

    @Override
    protected void processImage() {
        ParameterBlock pb = new ParameterBlock();
        pb.addSource(originalImage);
        pb.add(medianFilterShape);
        pb.add(maskSize);

        editedImage = JAI.create("MedianFilter", pb);
        editedImage.setProperty("offsetX", originalImage.getProperty("offsetX"));
        editedImage.setProperty("offsetY", originalImage.getProperty("offsetY"));
    }
}
