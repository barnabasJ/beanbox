package net.jovacorp.bmj.beanbox.bean;

import net.jovacorp.bmj.beanbox.event.ImageEvent;
import net.jovacorp.bmj.beanbox.listener.ImageListener;

import javax.media.jai.PlanarImage;
import java.awt.*;

public class ImageDisplay extends Panel implements ImageListener {
    private PlanarImage image;

    public ImageDisplay() {
        setSize(200, 200);
        setBackground(Color.PINK);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (image == null) {
            return;
        }

        g.drawImage(image.getAsBufferedImage(), 0, 0, null);
    }

    @Override
    public void sourceChanged(ImageEvent event) {
        if (event.getImage() != null) {
            image = event.getImage();
        }

        repaint();
    }
}
