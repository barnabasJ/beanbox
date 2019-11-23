package net.jovacorp.bmj.beanbox.bean;

import javax.media.jai.PlanarImage;
import java.awt.*;

public class ImageROI extends AbstractImageProcessBean {

    private int offsetX;
    private int offsetY;
    private int width;
    private int height;

    public ImageROI() {
        super();

        offsetX = 50;
        offsetY = 50;
        width = 370;
        height = 50;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
        process();
    }

    public int getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
        process();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
        process();
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        process();
    }

    @Override
    protected void processImage() {
        Rectangle rectangle = new Rectangle(offsetX, offsetY, width, height);
        editedImage = PlanarImage.wrapRenderedImage(originalImage.getAsBufferedImage(rectangle, originalImage.getColorModel()));
        editedImage.setProperty("offsetX", offsetX);
        editedImage.setProperty("offsetY", offsetY);
    }
}
