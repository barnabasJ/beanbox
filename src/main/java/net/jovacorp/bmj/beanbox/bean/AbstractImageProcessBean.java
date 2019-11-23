package net.jovacorp.bmj.beanbox.bean;

import net.jovacorp.bmj.beanbox.event.ImageEvent;
import net.jovacorp.bmj.beanbox.listener.ImageListener;

import javax.media.jai.PlanarImage;
import java.util.Vector;

public abstract class AbstractImageProcessBean implements ImageListener {

    protected PlanarImage originalImage;
    protected PlanarImage editedImage;
    protected Vector<ImageListener> listeners;

    public AbstractImageProcessBean() {
        listeners = new Vector<>();
    }

    @Override
    public void sourceChanged(ImageEvent event) {
        if (event.getImage() != null) {
            originalImage = event.getImage();
            process();
        }
    }

    protected void process() {
        if (originalImage == null) {
            return;
        }

        processImage();

        fireImageEvent();
    }

    protected abstract void processImage();

    public void addImageListener(ImageListener listener) {
        if (listeners == null) {
            listeners = new Vector<>();
        }
        listeners.add(listener);

        fireImageEvent();
    }

    public void removeImageListener(ImageListener listener) {
        if (listeners != null) {
            listeners.remove(listener);
        }
    }

    protected void fireImageEvent() {
        if (editedImage == null) {
            return;
        }

        ImageEvent event = new ImageEvent(this, editedImage);
        for (ImageListener listener : listeners) {
            listener.sourceChanged(event);
        }
    }
}
