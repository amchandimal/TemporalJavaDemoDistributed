package com.chandimal.orderservice.workflows.placeorder.workers;

import com.chandimal.orderservice.workflows.placeorder.PlaceOrderWorkflowImpl;
import com.chandimal.orderservice.workflows.placeorder.activities.PlaceOrderActivities;
import com.chandimal.orderservice.workflows.placeorder.activities.PlaceOrderActivitiesImpl;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class PlaceOrderWorker {

  @PostConstruct
  public void init(){
    WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
    WorkflowClient client = WorkflowClient.newInstance(service);

    WorkerFactory factory = WorkerFactory.newInstance(client);
    Worker worker = factory.newWorker(PlaceOrderActivities.TASK_LIST);
    worker.registerWorkflowImplementationTypes(PlaceOrderWorkflowImpl.class);
    worker.registerActivitiesImplementations(new PlaceOrderActivitiesImpl());

    factory.start();
    System.out.println("Worker started for task queue: " + PlaceOrderActivities.TASK_LIST);
  }

}
