package io.changock.driver.es.v7.repository;

import io.changock.driver.api.entry.ChangeEntry;
import io.changock.driver.core.entry.ChangeEntryRepository;
import io.changock.migration.api.exception.ChangockException;
import io.changock.utils.field.FieldInstance;

import java.util.List;

public class EsChangeEntryRepository <CHANGE_ENTRY extends ChangeEntry>
    extends EsRepositoryBase<CHANGE_ENTRY, ChangeEntryEntity>
    implements ChangeEntryRepository<CHANGE_ENTRY, ChangeEntryEntity> {


  @Override
  public boolean isNewChange(String changeSetId, String author) throws ChangockException {
    return false;
  }

  @Override
  public void save(CHANGE_ENTRY changeEntry) throws ChangockException {

  }

  @Override
  public ChangeEntryEntity mapFieldInstances(List<FieldInstance> fieldInstanceList) {
    return null;
  }

  @Override
  public void initialize() {

  }
}
