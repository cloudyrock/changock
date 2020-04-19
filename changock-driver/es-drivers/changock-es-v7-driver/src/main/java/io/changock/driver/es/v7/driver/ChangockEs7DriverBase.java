package io.changock.driver.es.v7.driver;

import io.changock.driver.api.driver.ChangeSetDependency;
import io.changock.driver.api.entry.ChangeEntry;
import io.changock.driver.api.lock.LockManager;
import io.changock.driver.core.driver.ConnectionDriverBase;
import io.changock.driver.core.lock.LockRepository;
import io.changock.driver.core.lock.interceptor.proxy.LockGuardProxy;
import io.changock.migration.api.exception.ChangockException;
import io.changock.utils.annotation.NotThreadSafe;
import org.elasticsearch.client.Client;

import java.util.HashSet;
import java.util.Set;

/**
 *
 */
//TODO This base class is needed because it will used  as foundation by the pro version.
// Extend this class to provide the actual driver: ChangockEs7Driver
// Annotate with {@link  NotThreadSafe} if it's not synchronized
public abstract class ChangockEs7DriverBase<CHANGE_ENTRY extends ChangeEntry> extends ConnectionDriverBase<CHANGE_ENTRY> {

  private final Client esClient;

  public ChangockEs7DriverBase(Client esClient) {
    this.esClient = esClient;
  }

  @Override
  protected LockRepository getLockRepository() {
    //TODO returns lockRepository based on esClient
    // It will be called at initialize()
    // Ensure idempotency
    return null;
  }


  @Override
  public void runValidation() throws ChangockException {
    //TODO perform required validation before migration, like esClint not null, etc.
    // This will be executed right after initialize()
  }

  @Override
  public Set<ChangeSetDependency> getDependencies() {
    LockManager lockManager = this.getLockManager();
    Set<ChangeSetDependency> dependencies = new HashSet<>();
    Client esClientDecorator = LockGuardProxy.getProxy(esClient, Client.class, lockManager);
    dependencies.add(new ChangeSetDependency(Client.class, esClientDecorator));
    return dependencies;
  }
}
