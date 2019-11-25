package net.jovacorp.bmj.beanbox.listener;

import java.util.EventListener;

public interface Listener<E> extends EventListener {

  void sourceChanged(E event);
}
