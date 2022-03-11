package com.chandimal.orderservice.workflows.placeorder;

import com.chandimal.orderservice.workflows.placeorder.activities.AccountServiceActivities;
import com.chandimal.orderservice.workflows.placeorder.activities.DeliveryServiceActivities;
import com.chandimal.orderservice.workflows.placeorder.activities.PlaceOrderActivities;
import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.failure.ActivityFailure;
import io.temporal.workflow.Saga;
import io.temporal.workflow.Workflow;
import java.time.Duration;

public class PlaceOrderWorkflowImpl implements PlaceOrderWorkflow{

  private final ActivityOptions placeOrderActivityoptions =
      ActivityOptions.newBuilder()
          .setStartToCloseTimeout(Duration.ofHours(1))
          .setTaskQueue(PlaceOrderActivities.TASK_LIST)
          .setRetryOptions(RetryOptions.newBuilder().setMaximumAttempts(1).build())
          .build();
  private final PlaceOrderActivities placeOrderActivities =
      Workflow.newActivityStub(PlaceOrderActivities.class, placeOrderActivityoptions);


  private final ActivityOptions accountServiceActivityoptions =
      ActivityOptions.newBuilder()
          .setStartToCloseTimeout(Duration.ofHours(1))
          .setTaskQueue(AccountServiceActivities.TASK_LIST)
          .setRetryOptions(RetryOptions.newBuilder().setMaximumAttempts(1).build())
          .build();
  private final AccountServiceActivities accountServiceActivities =
      Workflow.newActivityStub(AccountServiceActivities.class, accountServiceActivityoptions);

  private final ActivityOptions deliveryServiceActivityoptions =
      ActivityOptions.newBuilder()
          .setStartToCloseTimeout(Duration.ofHours(1))
          .setTaskQueue(DeliveryServiceActivities.TASK_LIST)
          .setRetryOptions(RetryOptions.newBuilder().setMaximumAttempts(1).build())
          .build();
  private final DeliveryServiceActivities deliveryServiceActivities =
      Workflow.newActivityStub(DeliveryServiceActivities.class, deliveryServiceActivityoptions);

  @Override
  public String placeOrder(String customerId) {
    Saga.Options sagaOptions = new Saga.Options.Builder().setParallelCompensation(true).build();
    Saga saga = new Saga(sagaOptions);
    try {
      //Sleeping for 5 seconds
      //Don't Use Thread.sleep Here
      Workflow.sleep(5000);

      if(placeOrderActivities.isValidUser(customerId)){
        if (accountServiceActivities.isBalanceSufficient(customerId)) {
          //Adding compensation
          saga.addCompensation(accountServiceActivities::revertTransaction,customerId);
          // Making a Transaction
          accountServiceActivities.makeTransaction(customerId);
          return deliveryServiceActivities.deliverOrder(customerId);
        }else {
          return "ERROR_OCCURED";
        }
      }else {
        return "INVALID_USER";
      }
    } catch (ActivityFailure e) {
      saga.compensate();
      e.printStackTrace();
      return "ERROR_OCCURED";
    }
  }
}
