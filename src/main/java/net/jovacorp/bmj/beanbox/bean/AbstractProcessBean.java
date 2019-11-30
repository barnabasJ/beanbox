package net.jovacorp.bmj.beanbox.bean;

import net.jovacorp.bmj.beanbox.event.Event;
import net.jovacorp.bmj.beanbox.listener.Listener;

import java.util.Vector;

public abstract class AbstractProcessBean<I, E extends Event<I>, O> implements Listener<E> {
  protected I inputData;
  protected Vector<Listener> listeners;

  public AbstractProcessBean() {
    this.listeners = new Vector<>();
  }

  @Override
  public void sourceChanged(E event) {
    if (event.getEventData() != null) {
      inputData = event.getEventData();
      process();
    }
  }

  protected void process() {
    if (inputData == null) {
      return;
    }

    fireEvent(processData(inputData));
  }

  protected abstract O processData(I inputData);

  public void addProcessListener(Listener<Event<O>> listener) {
    if (listeners == null) {
      listeners = new Vector<>();
    }
    listeners.add(listener);

    process();
  }

  public void removeProcessListener(Listener<Event<O>> listener) {
    if (listeners != null) {
      listeners.remove(listener);
    }
  }

  protected void fireEvent(O outputData) {
    if (outputData == null) {
      return;
    }
    Event<O> event = new Event<>(this, outputData);
    for (Listener listener : listeners) {
      listener.sourceChanged(event);
    }
  }
}
