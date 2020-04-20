package io.changock.driver.es.v7.driver.changelogs.basic;

import io.changock.migration.api.annotations.ChangeLog;
import io.changock.migration.api.annotations.ChangeSet;
import org.elasticsearch.client.Client;

@ChangeLog
public class ChangeLogBasic {

  @ChangeSet(author = "testuser", id = "method_0", order = "01")
  public void method_0() {
    System.out.println(ChangeLogBasic.class.getCanonicalName() + ".method_0()");
  }

  @ChangeSet(author = "testuser", id = "method_1", order = "02")
  public void method_1() {
    System.out.println(ChangeLogBasic.class.getCanonicalName() + ".method_1()");
  }

  @ChangeSet(author = "testuser", id = "method_3_runAlways", order = "03", runAlways = true)
  public void method_2_runAlways() {
    System.out.println(ChangeLogBasic.class.getCanonicalName() + ".method_2_runAlways()");
  }

  @ChangeSet(author = "testuser", id = "method_3_dbClient", order = "04")
  public void method_3_dbClient(Client dbClient) {
    System.out.println(
        String.format("%s.method_3_dbClient(%s) with %s", ChangeLogBasic.class.getCanonicalName(), dbClient.getClass().getName(), dbClient.toString()));
  }

}
