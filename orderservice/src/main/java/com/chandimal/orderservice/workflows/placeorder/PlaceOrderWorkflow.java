package com.chandimal.orderservice.workflows.placeorder;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface PlaceOrderWorkflow {
  @WorkflowMethod
  String placeOrder(String customerId);
}
