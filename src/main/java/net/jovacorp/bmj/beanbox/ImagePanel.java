package net.jovacorp.bmj.beanbox;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ImagePanel extends Panel {

    File file;

    public void displayImage(File file) {
        this.file = file;

        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        try {
            Image image = ImageIO.read(file);
            g.drawImage(image, 0, 0, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
