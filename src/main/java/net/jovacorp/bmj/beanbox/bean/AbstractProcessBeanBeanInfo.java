package net.jovacorp.bmj.beanbox.bean;

import net.jovacorp.bmj.beanbox.listener.Listener;

import java.beans.EventSetDescriptor;
import java.beans.IntrospectionException;
import java.beans.SimpleBeanInfo;

public class AbstractProcessBeanBeanInfo extends SimpleBeanInfo {
  @Override
  public EventSetDescriptor[] getEventSetDescriptors() {
    try {
      return new EventSetDescriptor[] {
        new EventSetDescriptor(
            AbstractProcessBean.class,
            "processed",
            Listener.class,
            new String[] {"sourceChanged"},
            "addProcessListener",
            "removeProcessListener")
      };
    } catch (IntrospectionException e) {
      e.printStackTrace();
    }
    return new EventSetDescriptor[] {};
  }
}
