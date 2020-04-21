package org.elasticsearch.client;


import io.changock.driver.api.lock.LockManager;
import org.apache.http.Header;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class RestClientDecorator extends RestClient {

  private final RestClient impl;
  private final LockManager lockManager;



  public RestClientDecorator(RestClient client, LockManager lockManager) {
    super(null, new Header[]{}, client.getNodes(), null, null, null, false);
    this.impl = client;
    this.lockManager = lockManager;
  }

  @Override
  public void setNodes(Collection<Node> nodes) {
  }

  @Override
  public List<Node> getNodes() {
    throw new UnsupportedOperationException("setNodes not supported for RestClientDecorator");
  }

  @Override
  public Response performRequest(Request request) throws IOException {
    lockManager.ensureLockDefault();
    return impl.performRequest(request);
  }


  @Override
  public Cancellable performRequestAsync(Request request, ResponseListener responseListener) {
    lockManager.ensureLockDefault();
    return impl.performRequestAsync(request, responseListener);
  }

  @Override
  public void close() throws IOException {
    impl.close();
  }

}
