package io.changock.driver.es.v7.driver;

import io.changock.driver.api.entry.ChangeEntry;
import io.changock.driver.api.entry.ChangeEntryService;
import org.elasticsearch.client.Client;

public class ChangockEs7Driver extends ChangockEs7DriverBase<ChangeEntry> {

  public ChangockEs7Driver(Client esClient) {
    super(esClient);
  }

  @Override
  public ChangeEntryService<ChangeEntry> getChangeEntryService() {
    return null;
  }
}
