package com.chandimal.orderservice.workflows.placeorder.activities;

import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class PlaceOrderActivitiesImpl implements PlaceOrderActivities{

  private static Map<String,Double> accountBalances;

  static {
    accountBalances = new HashMap<>();
    accountBalances.put("Chandimal",100d);
    accountBalances.put("Alex",120d);
    accountBalances.put("Kevin",50d);
  }

 @Override
  public boolean isValidUser(String userId) {
    if(accountBalances.containsKey(userId)){
      return true;
    }
    return false;
  }
}
