package com.chandimal.orderservice.workflows.placeorder.activities;

import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface PlaceOrderActivities {
  public static String TASK_LIST = "PLACEORDER_TASK_LIST";

  boolean isValidUser(String userId);
}
