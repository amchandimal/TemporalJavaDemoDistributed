package com.chandimal.deliveryservice.workflows.activities;

import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface DeliveryServiceActivities {

   public static String TASK_LIST = "DELIVERYSERVICE_TASK_LIST";

   public String deliverOrder(String customerId);
}
