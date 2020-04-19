package io.changock.driver.es.v7.decorator;

import io.changock.driver.core.decorator.LockMethodInvoker;
import org.elasticsearch.client.Client;

public interface ClientDecorator extends Client {

  static Client getProxy(Client implementation, LockMethodInvoker invoker) {
    //TODO return a decorator. It can use java interceptor or manual decorator
    Client proxy = implementation;
    return proxy;
  }
}
