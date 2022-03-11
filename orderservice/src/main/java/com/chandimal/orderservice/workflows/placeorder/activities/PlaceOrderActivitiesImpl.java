package com.chandimal.orderservice.workflows.placeorder.activities;

import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlaceOrderActivitiesImpl implements PlaceOrderActivities{

  private static final Logger logger = LoggerFactory.getLogger(PlaceOrderActivitiesImpl.class);

  private static Map<String,Double> accountBalances;

  static {
    accountBalances = new HashMap<>();
    accountBalances.put("Chandimal",100d);
    accountBalances.put("Alex",120d);
    accountBalances.put("Kevin",50d);
    accountBalances.put("Joe",500d);
  }

 @Override
  public boolean isValidUser(String userId) {
   logger.info("Checking Validity of UserId: "+userId);
    if(accountBalances.containsKey(userId)){
      logger.info("UserId: "+userId+" Is Valid!");
      return true;
    }
   logger.info("UserId: "+userId+" Is Not Valid!");
    return false;
  }
}
