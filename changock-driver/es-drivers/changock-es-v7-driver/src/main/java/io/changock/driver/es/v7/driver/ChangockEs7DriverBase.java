package io.changock.driver.es.v7.driver;

import io.changock.driver.api.driver.ChangeSetDependency;
import io.changock.driver.api.entry.ChangeEntry;
import io.changock.driver.api.lock.LockManager;
import io.changock.driver.core.decorator.LockMethodInvoker;
import io.changock.driver.core.driver.ConnectionDriverBase;
import io.changock.driver.core.lock.LockRepository;
import io.changock.driver.es.v7.decorator.ClientDecorator;
import io.changock.migration.api.exception.ChangockException;
import io.changock.utils.annotation.NotThreadSafe;
import org.elasticsearch.client.Client;

import java.util.HashSet;
import java.util.Set;

/**
 * This base class is needed because it will used by professional edition projects.
 * Extend this class to provide the actual driver: ChangockEs7Driver
 * TODO annotated with {@link  NotThreadSafe} if it's not synchronized
 */
public abstract class ChangockEs7DriverBase<CHANGE_ENTRY extends ChangeEntry> extends ConnectionDriverBase<CHANGE_ENTRY> {

  private final Client esClient;

  public ChangockEs7DriverBase(Client esClient) {
    this.esClient = esClient;
  }

  /**
   * retrieves
   */
  @Override
  protected LockRepository getLockRepository() {
    //TODO returns lockRepository base on esClient
    return null;
  }


  @Override
  public Set<ChangeSetDependency> getDependencies() {
    LockManager lockManager = this.getLockManager();
    LockMethodInvoker invoker = new LockMethodInvoker(lockManager);
    Set<ChangeSetDependency> dependencies = new HashSet<>();
    Client esClientDecorator = ClientDecorator.getProxy(esClient, invoker);
    dependencies.add(new ChangeSetDependency(Client.class, esClientDecorator));
    return dependencies;
  }

  @Override
  public void runValidation() throws ChangockException {
    //TODO perform any validation before stating migration. Like es client is not null
  }
}
