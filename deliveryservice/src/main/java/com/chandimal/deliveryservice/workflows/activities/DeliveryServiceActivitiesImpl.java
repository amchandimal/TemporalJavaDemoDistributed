package com.chandimal.deliveryservice.workflows.activities;

import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeliveryServiceActivitiesImpl implements DeliveryServiceActivities{

  private static final Logger logger = LoggerFactory.getLogger(DeliveryServiceActivitiesImpl.class);

  @Override
  public String deliverOrder(String customerId) {
    if (customerId.length() > 3){
      logger.info("Order_Delivered! for UserId: "+customerId);
      return "Order_Delivered! Order Id: "+ UUID.randomUUID().toString();
    }else {
      logger.error("Order Delivery Failed! for UserId: "+customerId);
      throw new RuntimeException("Order_Delivery_Failed");
    }
  }
}
