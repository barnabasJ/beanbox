package net.jovacorp.bmj.beanbox.bean;

import net.jovacorp.bmj.beanbox.event.Event;
import net.jovacorp.bmj.beanbox.listener.Listener;

import javax.media.jai.PlanarImage;
import java.awt.*;

public class ImageDisplay extends Panel implements Listener<Event<PlanarImage>> {
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
  public void sourceChanged(Event<PlanarImage> event) {
    if (event.getEventData() != null) {
      image = event.getEventData();
    }

    repaint();
  }
}
