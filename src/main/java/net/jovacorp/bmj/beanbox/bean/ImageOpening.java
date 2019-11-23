package net.jovacorp.bmj.beanbox.bean;

import javax.media.jai.KernelJAI;
import javax.media.jai.operator.DilateDescriptor;
import javax.media.jai.operator.ErodeDescriptor;

public class ImageOpening extends AbstractImageProcessBean {

    private float[] kernelMatrix;
    private int kernelDimensionX;
    private int kernelDimensionY;
    private int executionAmount;

    public ImageOpening() {
        super();

        kernelMatrix = new float[]{
                0, 1, 0,
                1, 1, 1,
                0, 1, 0
        };

        kernelDimensionX = 3;
        kernelDimensionY = 3;
        executionAmount = 4;
    }

    public int getExecutionAmount() {
        return executionAmount;
    }

    public void setExecutionAmount(int executionAmount) {
        this.executionAmount = executionAmount;
        process();
    }

    @Override
    protected void processImage() {
        KernelJAI kernel = new KernelJAI(kernelDimensionX, kernelDimensionY, kernelMatrix);

        // erode
        editedImage = ErodeDescriptor.create(originalImage, kernel, null);
        for (int i = 0; i < executionAmount - 1; i++) {
            editedImage = ErodeDescriptor.create(editedImage, kernel, null);
        }

        //dilate
        for (int i = 0; i < executionAmount; i++) {
            editedImage = DilateDescriptor.create(editedImage, kernel, null);
        }

        editedImage.setProperty("offsetX", originalImage.getProperty("offsetX"));
        editedImage.setProperty("offsetY", originalImage.getProperty("offsetY"));
    }
}
