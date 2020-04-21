package io.changock.driver.es.v7.interceptor;

import io.changock.driver.api.lock.LockManager;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientDecorator;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.CheckedConsumer;
import org.elasticsearch.common.xcontent.NamedXContentRegistry;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

//TODO add test to check lock ensure
public class RestHighLevelClientDecorator extends RestHighLevelClient {

  public static RestHighLevelClient getProxy(RestClient restClient, LockManager lockManager) {
    return new RestHighLevelClientDecorator(new RestClientDecorator(restClient, lockManager), RestClient::close, Collections.emptyList());
  }

  private RestHighLevelClientDecorator(RestClient restClient, CheckedConsumer<RestClient, IOException> doClose, List<NamedXContentRegistry.Entry> namedXContentEntries) {
    super(restClient, doClose, namedXContentEntries);
  }

}
