package net.jovacorp.bmj.beanbox.bean;

import javax.media.jai.JAI;
import java.awt.image.renderable.ParameterBlock;

public class ImageThreshold extends AbstractImageProcessBean {

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
    protected void processImage() {
        ParameterBlock pb = new ParameterBlock();
        pb.addSource(originalImage);
        pb.add(new double[]{low});
        pb.add(new double[]{high});
        pb.add(new double[]{map});

        editedImage = JAI.create("threshold", pb);
        editedImage.setProperty("offsetX", originalImage.getProperty("offsetX"));
        editedImage.setProperty("offsetY", originalImage.getProperty("offsetY"));
    }
}
