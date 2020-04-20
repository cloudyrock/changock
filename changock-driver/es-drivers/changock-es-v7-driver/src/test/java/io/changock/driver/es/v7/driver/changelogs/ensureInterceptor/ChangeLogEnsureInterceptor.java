package io.changock.driver.es.v7.driver.changelogs.ensureInterceptor;

import io.changock.driver.es.v7.driver.util.CallVerifier;
import io.changock.migration.api.annotations.ChangeLog;
import io.changock.migration.api.annotations.ChangeSet;
import org.elasticsearch.client.Client;

@ChangeLog
public class ChangeLogEnsureInterceptor {


  @ChangeSet(author = "testuser", id = "id_duplicated", order = "00")
  public void method(Client mongodatabase, CallVerifier callVerifier) {
//    Assert.assertTrue(MongoDatabaseDecorator.class.isAssignableFrom(mongodatabase.getClass()));
    //TODO check is a decorator/proxy
    callVerifier.counter++;
  }

}
