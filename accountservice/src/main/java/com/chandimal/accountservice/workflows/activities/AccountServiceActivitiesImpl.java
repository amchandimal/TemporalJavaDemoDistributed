package com.chandimal.accountservice.workflows.activities;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountServiceActivitiesImpl implements AccountServiceActivities {

  private static final Logger logger = LoggerFactory.getLogger(AccountServiceActivitiesImpl.class);
  private static final double ITEM_PRICE = 100;
  private static final Map<String, Double> accountBalances;

  static {
    accountBalances = new HashMap<>();
    accountBalances.put("Chandimal", 100d);
    accountBalances.put("Alex", 120d);
    accountBalances.put("Kevin", 50d);
    accountBalances.put("Joe", 500d);
  }

  @Override
  public boolean isBalanceSufficient(String userId) {
    logger.info("Checking the Balance for UserId: " + userId);
    return accountBalances.get(userId) >= ITEM_PRICE;
  }

  @Override
  public boolean makeTransaction(String userId) {
    logger.info("Making Transaction for UserId: " + userId);
    try {
      logger.info("Taking Time of: 2500 millis");
      Thread.sleep(2500);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    accountBalances.put(userId, accountBalances.get(userId) - ITEM_PRICE);
    if (userId.length() % 2 == 0) {
      throw new RuntimeException("Making Transaction for UserId :" + userId + " Failed!");
    }
    logger.info("Current the Balance for UserId: " + userId + " = " + accountBalances.get(userId));
    return true;
  }

  @Override
  public boolean revertTransaction(String userId) {
    logger.info("Reverting Transaction for UserId: " + userId);
    accountBalances.put(userId, accountBalances.get(userId) + ITEM_PRICE);
    logger.info("Current the Balance for UserId: " + userId + " = " + accountBalances.get(userId));
    return true;
  }


}
