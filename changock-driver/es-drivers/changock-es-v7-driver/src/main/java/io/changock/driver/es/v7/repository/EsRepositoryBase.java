package io.changock.driver.es.v7.repository;

import io.changock.driver.core.common.Repository;



public abstract class EsRepositoryBase<DOMAIN_CLASS, ENTITY_CLASS> implements Repository<DOMAIN_CLASS, ENTITY_CLASS> {
  //TODO this is the super class og LockRepository and ChangeEntryRepository. It should ensures indexes, etc.
  //TODO Take a look to io.changock.driver.mongo.v3.core.repository.MongoRepositoryBase class
}
