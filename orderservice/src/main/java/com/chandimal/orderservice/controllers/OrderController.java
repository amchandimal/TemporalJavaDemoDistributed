package com.chandimal.orderservice.controllers;
import com.chandimal.orderservice.workflows.placeorder.PlaceOrderWorkflow;
import com.chandimal.orderservice.workflows.placeorder.activities.PlaceOrderActivities;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import java.time.Duration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

  private final WorkflowClient workflowClient;

  public OrderController(WorkflowClient workflowClient) {
    this.workflowClient = workflowClient;
  }

  @GetMapping("/sync/{customerId}")
  public ResponseEntity<String> createOrderSync(@PathVariable String customerId) {
    WorkflowOptions options = WorkflowOptions.newBuilder()
        .setTaskQueue(PlaceOrderActivities.TASK_LIST).build();
    PlaceOrderWorkflow workflow = workflowClient.newWorkflowStub(PlaceOrderWorkflow.class, options);
    return ResponseEntity.ok(workflow.placeOrder(customerId));
  }

  @GetMapping("/async/{customerId}")
  public ResponseEntity<String> createOrderAsync(@PathVariable String customerId) {
    WorkflowOptions options = WorkflowOptions.newBuilder()
        .setTaskQueue(PlaceOrderActivities.TASK_LIST).build();
    PlaceOrderWorkflow workflow = workflowClient.newWorkflowStub(PlaceOrderWorkflow.class, options);
    WorkflowClient.execute(workflow::placeOrder, customerId);
    return ResponseEntity.ok("Order Processing!");
  }

}
