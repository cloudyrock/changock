package io.changock.driver.es.v7.driver;

import io.changock.driver.api.driver.ChangeSetDependency;
import io.changock.driver.api.entry.ChangeEntry;
import io.changock.driver.api.lock.LockManager;
import io.changock.driver.core.driver.ConnectionDriverBase;
import io.changock.driver.core.lock.LockRepository;
import io.changock.driver.core.lock.guard.invoker.LockGuardInvoker;
import io.changock.driver.core.lock.guard.proxy.LockGuardProxy;
import io.changock.driver.es.v7.interceptor.RestHighLevelClientDecorator;
import io.changock.migration.api.exception.ChangockException;
import io.changock.utils.annotation.NotThreadSafe;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

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
  private final RestClient restClient;

  public ChangockEs7DriverBase(Client esClient) {
    this((RestClient)null, esClient);
  }

  public ChangockEs7DriverBase(RestClient restClient) {
    this(restClient, null);
  }

  public ChangockEs7DriverBase(RestClient restClient, Client esClient) {
    this.restClient = restClient;
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
    injectEsClientDependency(lockManager, dependencies);
    injectRestHighLevelClientDependency(lockManager, dependencies);
    return dependencies;
  }

  private void injectEsClientDependency(LockManager lockManager, Set<ChangeSetDependency> dependencies) {
    Client esClientDecorator = esClient != null ? LockGuardProxy.getProxy(esClient, Client.class, lockManager) : null;
    dependencies.add(new ChangeSetDependency(Client.class, esClientDecorator));
  }

  private void injectRestHighLevelClientDependency(LockManager lockManager, Set<ChangeSetDependency> dependencies) {
    RestHighLevelClient restHighLevelClientDecorator = restClient != null
        ? RestHighLevelClientDecorator.getProxy(restClient, lockManager)
        : null;
    dependencies.add(new ChangeSetDependency(RestHighLevelClient.class, restHighLevelClientDecorator));
  }
}
