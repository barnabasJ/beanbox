package net.jovacorp.bmj.beanbox.event;

import lombok.Getter;

import java.util.EventObject;

public class Event<D> extends EventObject {

  @Getter private D eventData;
  /**
   * Constructs a prototypical Event.
   *
   * @param source The object on which the Event initially occurred.
   * @throws IllegalArgumentException if source is null.
   */
  public Event(Object source, D eventData) {
    super(source);

    this.eventData = eventData;
  }
}
