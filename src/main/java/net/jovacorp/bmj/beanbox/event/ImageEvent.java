package net.jovacorp.bmj.beanbox.event;

import javax.media.jai.PlanarImage;

public class ImageEvent extends Event<PlanarImage> {
  /**
   * Constructs a prototypical Event.
   *
   * @param source The object on which the Event initially occurred.
   * @param eventData
   * @throws IllegalArgumentException if source is null.
   */
  public ImageEvent(Object source, PlanarImage eventData) {
    super(source, eventData);
  }
}
