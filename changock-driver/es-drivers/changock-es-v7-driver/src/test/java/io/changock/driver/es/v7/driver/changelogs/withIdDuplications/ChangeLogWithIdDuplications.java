package io.changock.driver.es.v7.driver.changelogs.withIdDuplications;

import io.changock.migration.api.annotations.ChangeLog;
import io.changock.migration.api.annotations.ChangeSet;
import org.elasticsearch.client.Client;

@ChangeLog
public class ChangeLogWithIdDuplications {

  @ChangeSet(author = "testuser", id = "id_duplicated", order = "00")
  public void method_0() {
    System.out.println(ChangeLogWithIdDuplications.class.getCanonicalName() + ".method_0()");
  }

  @ChangeSet(author = "testuser", id = "id_duplicated", order = "01")
  public void method_1() {
    System.out.println(ChangeLogWithIdDuplications.class.getCanonicalName() + ".method_1()");
  }


  @ChangeSet(author = "testuser", id = "ChangeLog1_4", order = "04")
  public void method_4(Client mongoDatabase) {
    System.out.println(ChangeLogWithIdDuplications.class.getCanonicalName() + ".method_4(MongoDatabase mongoDatabase)\n\t\twith " + mongoDatabase.toString());
  }

}
