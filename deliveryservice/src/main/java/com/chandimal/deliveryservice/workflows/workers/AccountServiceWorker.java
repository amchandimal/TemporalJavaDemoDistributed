package com.chandimal.deliveryservice.workflows.workers;

import com.chandimal.deliveryservice.workflows.activities.DeliveryServiceActivities;
import com.chandimal.deliveryservice.workflows.activities.DeliveryServiceActivitiesImpl;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class AccountServiceWorker {

  @PostConstruct
  public void init(){
    WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
    WorkflowClient client = WorkflowClient.newInstance(service);

    WorkerFactory factory = WorkerFactory.newInstance(client);
    Worker worker = factory.newWorker(DeliveryServiceActivities.TASK_LIST);
    worker.registerActivitiesImplementations(new DeliveryServiceActivitiesImpl());

    factory.start();
    System.out.println("Worker started for task queue: " + DeliveryServiceActivities.TASK_LIST);
  }

}
