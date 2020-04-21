package org.elasticsearch.client;

import io.changock.driver.api.lock.LockManager;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.nio.protocol.HttpAsyncRequestProducer;
import org.apache.http.nio.protocol.HttpAsyncResponseConsumer;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.util.concurrent.Future;

public class CloseableHttpAsyncClientDecorator extends CloseableHttpAsyncClient {

  private final CloseableHttpAsyncClient impl;
  private final LockManager lockManager;

  public CloseableHttpAsyncClientDecorator(CloseableHttpAsyncClient impl, LockManager lockManager) {
    this.impl = impl;
    this.lockManager = lockManager;
  }
  @Override
  public boolean isRunning() {
    return impl.isRunning();
  }

  @Override
  public void start() {
    impl.start();
  }

  @Override
  public void close() throws IOException {
    impl.close();
  }

  @Override
  public <T> Future<T> execute(HttpAsyncRequestProducer httpAsyncRequestProducer, HttpAsyncResponseConsumer<T> httpAsyncResponseConsumer, HttpContext httpContext, FutureCallback<T> futureCallback) {
    lockManager.ensureLockDefault();
    return impl.execute(httpAsyncRequestProducer, httpAsyncResponseConsumer, httpContext, futureCallback);
  }
}
