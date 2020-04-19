package io.changock.driver.es.v7.driver;

import io.changock.driver.api.entry.ChangeEntry;
import io.changock.driver.api.entry.ChangeEntryService;
import io.changock.driver.es.v7.repository.EsChangeEntryRepository;
import org.elasticsearch.client.Client;
import io.changock.utils.annotation.NotThreadSafe;

@NotThreadSafe
public class ChangockEs7Driver extends ChangockEs7DriverBase<ChangeEntry> {

  protected EsChangeEntryRepository<ChangeEntry> changeEntryRepository;


  public ChangockEs7Driver(Client esClient) {
    super(esClient);
  }

  //TODO implement this properly: constructor arguments, etc.
  @Override
  public ChangeEntryService<ChangeEntry> getChangeEntryService() {
    if (changeEntryRepository == null) {
      this.changeEntryRepository = new EsChangeEntryRepository<>();
    }
    return changeEntryRepository;
  }
}
