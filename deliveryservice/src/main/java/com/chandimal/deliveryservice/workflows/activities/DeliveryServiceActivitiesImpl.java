package com.chandimal.deliveryservice.workflows.activities;

public class DeliveryServiceActivitiesImpl implements DeliveryServiceActivities{

  @Override
  public String deliverOrder(String customerId) {
    if (customerId.length() %2 == 0){
      return "Order_Delivered";
    }else {
      return "Order_Delivery_Failed";
    }
  }
}
