package net.jovacorp.bmj.beanbox.listener;

import net.jovacorp.bmj.beanbox.event.ImageEvent;

import java.util.EventListener;

public interface ImageListener extends EventListener {

    void sourceChanged(ImageEvent event);
}
