package net.jovacorp.bmj.beanbox.bean;

import net.jovacorp.bmj.beanbox.listener.Listener;

import java.beans.EventSetDescriptor;
import java.beans.IntrospectionException;
import java.beans.SimpleBeanInfo;

public class ImageChooserBeanInfo extends SimpleBeanInfo {
  @Override
  public EventSetDescriptor[] getEventSetDescriptors() {
    try {
      return new EventSetDescriptor[] {
        new EventSetDescriptor(
            ImageChooser.class,
            "image",
            Listener.class,
            new String[] {"imageChanged"},
            "addImageListener",
            "removeImageListener")
      };
    } catch (IntrospectionException e) {
      e.printStackTrace();
    }
    return new EventSetDescriptor[] {};
  }
}
