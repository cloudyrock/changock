package io.changock.driver.es.v7.driver;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * To run this tests, it should bbe as close to a real scenario as possible. So it should be run with
 * the testRunner and take real changeLogs classes.
 * For database, it recommended test container
 */
class MongoDriverITest {

  @Test
  @DisplayName("should run all changeLogs successfully")
  void shouldRunAllChangeLogsSuccessfully() {
    // USE ChangeLogBasic
    // PRECONDITION: None of the changeSets have been processed yet
    // RESULT: All the changeSets should be executed and tracked as EXECUTED with the right data
  }

  @Test
  @DisplayName("should ignore changeSet when is already executed if not runAlways")
  void shouldIgnoreChangeSet_WhenAlreadyExecuted_IfNotRunAlways()  {
    // USE ChangeLogBasic
    // PRECONDITION: One of the non-runAlways-changeSets to be processed should be already in the database with state == EXECUTED
    // RESULT: That changeSet should not be executed again but tracked as IGNORED in this new migration execution, as well as the rest of the changeSets in the changeLogs
  }

  @Test
  @DisplayName("should execute changeSet when is already executed if runAlways")
  void shouldExecuteChangeSet_WhenAlreadyExecuted_IfRunAlways()  {
    // USE ChangeLogBasic
    // PRECONDITION: One of the runAlways-changeSets to be processed should be already in the database with state == EXECUTED
    // RESULT: That changeSet should be executed again and tracked as EXECUTED in this new migration execution, as well as the rest of the changeSets in the changeLogs
  }

  @Test
  @DisplayName("should execute changeSet when is ignored executed if not runAlways")
  void shouldExecuteChangeSet_WhenAlreadyIgnored_IfNotRunAlways()  {
    // USE ChangeLogBasic
    // PRECONDITION: One of the non-runAlways-changeSets to be processed should be already in the database only with state == IGNORED
    // RESULT: That changeSet should be executed and tracked as EXECUTED in this new migration execution, as well as the rest of the changeSets in the changeLogs
  }

  @Test
  @DisplayName("should execute changeSet when is already ignored if runAlways")
  void shouldExecuteChangeSet_WhenAlreadyIgnored_IfRunAlways()  {
    // USE ChangeLogBasic
    // PRECONDITION: One of the runAlways-changeSets to be processed should be already in the database only with state == IGNORED
    // RESULT: That changeSet should be executed but tracked as EXECUTED in this new migration execution, as well as the rest of the changeSets in the changeLogs
  }

  @Test
  @DisplayName("should execute changeSet when is already failed if not runAlways")
  void shouldExecuteChangeSet_WhenAlreadyFailed_IfNotRunAlways()  {
    // USE ChangeLogBasic
    // PRECONDITION: One of the non-runAlways-changeSets to be processed should be already in the database only with state == FAILED
    // RESULT: That changeSet should be executed and tracked as EXECUTED in this new migration execution, as well as the rest of the changeSets in the changeLogs
  }

  @Test
  @DisplayName("should execute changeSet when is already failed if runAlways")
  void shouldExecuteChangeSet_WhenAlreadyFailed_IfRunAlways()  {
    // USE ChangeLogBasic
    // PRECONDITION: One of the runAlways-changeSets to be processed should be already in the database only with state == FAILED
    // RESULT: That changeSet should be executed and tracked as EXECUTED in this new migration execution, as well as the rest of the changeSets in the changeLogs
  }

  @Test
  @DisplayName("should pass interceptors as changeSet parameter when driver connection dependencies")
  void shouldPassInterceptorsAsChangeSetParameter_WhenIsDriverConnectionDependency() {
    // USE ChangeLogEnsureInterceptor and use the driver connection dependencies(As example, for Mongo spring data, it would need to ensure
    //     interceptor for MongoDatabase and MongoTemplate
    // PRECONDITION: changeSet method have driver connection dependencies as parameter
    // RESULT: Parameter injected to the changeSet method should be the interceptor(any way provided: decorator, proxy, etc.)
  }

  //TODO provide any other relevant test to this driver. For example, on mongo driver, there are some extra methods, like
  // change changeLogCollectionName and lockCollectionName(equivalent mechanisms need to be provided)
  // that are required to be tested.
}
