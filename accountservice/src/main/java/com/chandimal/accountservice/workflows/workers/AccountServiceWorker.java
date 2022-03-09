package com.chandimal.accountservice.workflows.workers;

import com.chandimal.accountservice.workflows.activities.AccountServiceActivities;
import com.chandimal.accountservice.workflows.activities.AccountServiceActivitiesImpl;
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
    Worker worker = factory.newWorker(AccountServiceActivities.TASK_LIST);
    worker.registerActivitiesImplementations(new AccountServiceActivitiesImpl());

    factory.start();
    System.out.println("Worker started for task queue: " + AccountServiceActivities.TASK_LIST);
  }

}
