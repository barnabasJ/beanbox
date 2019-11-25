package net.jovacorp.bmj.beanbox.bean;

import com.google.gson.Gson;
import net.jovacorp.bmj.beanbox.event.Event;
import net.jovacorp.bmj.beanbox.listener.Listener;

import java.lang.reflect.ParameterizedType;
import java.util.Vector;

public abstract class AbstractProcessBean<I, E extends Event<I>, O> implements Listener<E> {

  private final Gson gson;
  private final Class<O> outputClazz;
  protected I inputData;
  protected Vector<Listener> listeners;

  public AbstractProcessBean() {
    this.listeners = new Vector<>();
    this.gson = new Gson();
    this.outputClazz =
        (Class<O>)
            ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[2];
    System.out.println(outputClazz);
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

    fireImageEvent(processData(inputData));
  }

  protected abstract O processData(I inputData);

  public void addImageListener(Listener listener) {
    if (listeners == null) {
      listeners = new Vector<>();
    }
    listeners.add(listener);

    process();
  }

  public void removeImageListener(Listener listener) {
    if (listeners != null) {
      listeners.remove(listener);
    }
  }

  protected void fireImageEvent(O outputData) {
    if (outputData == null) {
      return;
    }

    for (Listener listener : listeners) {
      O eventData = gson.fromJson(gson.toJson(outputData), outputClazz);
      listener.sourceChanged(new Event<O>(this, eventData));
    }
  }
}
