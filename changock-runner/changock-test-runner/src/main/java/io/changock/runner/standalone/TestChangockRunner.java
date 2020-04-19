package io.changock.runner.standalone;

import io.changock.driver.api.driver.ChangeSetDependency;
import io.changock.driver.api.driver.ConnectionDriver;
import io.changock.runner.core.ChangeLogService;
import io.changock.runner.core.DependencyManager;
import io.changock.runner.core.MigrationExecutor;
import io.changock.runner.core.builder.DriverBuilderConfigurable;
import io.changock.runner.core.ChangockBase;
import io.changock.runner.core.builder.RunnerBuilderBase;

public class TestChangockRunner extends ChangockBase {

  public static DriverBuilderConfigurable<Builder, ConnectionDriver> builder() {
    return new Builder();
  }

  public static TestChangockRunner.Builder testBuilder() {
    return new Builder();
  }

  private TestChangockRunner(MigrationExecutor executor, ChangeLogService changeLogService, boolean throwExceptionIfCannotObtainLock, boolean enabled) {
    super(executor, changeLogService, throwExceptionIfCannotObtainLock, enabled);
  }

  public static class Builder extends RunnerBuilderBase<Builder, ConnectionDriver> {

    private DependencyManager dependencyManager = new DependencyManager();
    private String executionId;

    private Builder() {
    }

    public Builder addDependency(Class<?> type, Object instance) {
      this.dependencyManager.addStandardDependency(new ChangeSetDependency(type, instance));
      return returnInstance();
    }

    public Builder setExecutionId(String executionId) {
      this.executionId = executionId;
      return this;
    }

    public TestChangockRunner build() {
      return build(buildExecutorForTest(), buildChangeLogServiceDefault(), throwExceptionIfCannotObtainLock, enabled);
    }

    protected MigrationExecutor buildExecutorForTest() {
      return new TestMigrationExecutor(
          executionId,
          driver,
          dependencyManager,
          lockAcquiredForMinutes,
          maxTries,
          maxWaitingForLockMinutes,
          metadata
      );
    }


    public TestChangockRunner build(MigrationExecutor executor, ChangeLogService changeLogService, boolean throwExceptionIfCannotObtainLock, boolean enabled) {
      return new TestChangockRunner(executor, changeLogService, throwExceptionIfCannotObtainLock, enabled);
    }

    @Override
    protected Builder returnInstance() {
      return this;
    }
  }
}