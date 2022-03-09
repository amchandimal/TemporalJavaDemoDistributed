package com.chandimal.accountservice.workflows.activities;

import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface AccountServiceActivities {
  public static String TASK_LIST = "ACCOUNTSERVICE_TASK_LIST";

  boolean isBalanceSufficient(String userId);
  boolean makeTransaction(String userId);
  boolean revertTransaction(String userId);
}
