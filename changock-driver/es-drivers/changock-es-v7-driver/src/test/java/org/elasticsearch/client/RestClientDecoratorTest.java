package org.elasticsearch.client;


import io.changock.driver.api.lock.LockManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.internal.verification.Times;

import java.io.IOException;
import java.util.Collections;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

 class RestClientDecoratorTest {

   private RestClientDecorator restClientDecorator;
  private LockManager lockManager;

  @BeforeEach
  void before() {
    RestClient restClientImpl = mock(RestClient.class);
    when(restClientImpl.getNodes()).thenReturn(Collections.emptyList());
    lockManager = mock(LockManager.class);
    restClientDecorator = new RestClientDecorator(restClientImpl, lockManager);
  }

  @Test
  @DisplayName("should ensure lock when calling performRequest(Request)")
  void shouldEnsureLock_whenPerformRequest() throws IOException {
    // when
    restClientDecorator.performRequest(new Request("POST", "endpoint"));

    // then
    verify(lockManager, new Times(1)).ensureLockDefault();
  }
  @Test
  @DisplayName("should ensure lock when calling performRequestAsync(Request)")
  void shouldEnsureLock_whenPerformRequestAsync() throws IOException {
    // when
    restClientDecorator.performRequestAsync(new Request("POST", "endpoint"),mock(ResponseListener.class));

    // then
    verify(lockManager, new Times(1)).ensureLockDefault();
  }


}
