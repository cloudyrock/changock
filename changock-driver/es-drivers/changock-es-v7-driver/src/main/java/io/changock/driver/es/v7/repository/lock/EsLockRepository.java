package io.changock.driver.es.v7.repository.lock;

import io.changock.driver.core.lock.LockEntry;
import io.changock.driver.core.lock.LockPersistenceException;
import io.changock.driver.core.lock.LockRepository;
import io.changock.driver.es.v7.repository.EsRepositoryBase;
import io.changock.utils.field.FieldInstance;

import java.util.List;

public class EsLockRepository extends EsRepositoryBase<LockEntry, LockEntity> implements LockRepository<LockEntity> {

  @Override
  public void insertUpdate(LockEntry newLock) throws LockPersistenceException {

  }

  @Override
  public void updateIfSameOwner(LockEntry newLock) throws LockPersistenceException {

  }

  @Override
  public LockEntry findByKey(String lockKey) {
    return null;
  }

  @Override
  public void removeByKeyAndOwner(String lockKey, String owner) {

  }

  @Override
  public LockEntity mapFieldInstances(List<FieldInstance> fieldInstanceList) {
    return null;
  }

  @Override
  public void initialize() {

  }
}
