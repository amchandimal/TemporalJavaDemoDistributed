package com.chandimal.accountservice.workflows.activities;

import java.util.HashMap;
import java.util.Map;

public class AccountServiceActivitiesImpl implements AccountServiceActivities {

  private static Map<String,Double> accountBalances;
  private static final double ITEM_PRICE = 100;

  static {
    accountBalances = new HashMap<>();
    accountBalances.put("Chandimal",100d);
    accountBalances.put("Alex",120d);
    accountBalances.put("Kevin",50d);
  }

  @Override
  public boolean isBalanceSufficient(String userId) {
    return accountBalances.get(userId) >= ITEM_PRICE;
  }

  @Override
  public boolean makeTransaction(String userId) {
    accountBalances.put(userId,accountBalances.get(userId)-ITEM_PRICE);
    return true;
  }

  @Override
  public boolean revertTransaction(String userId) {
    accountBalances.put(userId,accountBalances.get(userId)+ITEM_PRICE);
    return true;
  }


}
