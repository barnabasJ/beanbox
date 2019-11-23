package net.jovacorp.bmj.beanbox.event;

import javax.media.jai.PlanarImage;
import java.util.EventObject;

public class ImageEvent extends EventObject {

    private PlanarImage image;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ImageEvent(Object source, PlanarImage image) {
        super(source);

        this.image = image;
    }

    public PlanarImage getImage() {
        return image;
    }
}
