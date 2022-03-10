# Java Microservices with Temporal

## With Temporal 

### System will become more reliable
Fail to execute/drop data less often, When parts of application fails, it always recover to consistent state

### More Productive
Few lines of code & infrastructure when writing features. Orchestration outsourced to Temporal

### Easier to operate
Consolidates errors, lets make fixes without downtime.Highly observable by default.

## Demo App

![image](https://user-images.githubusercontent.com/20762013/157609799-433638d2-dc07-4521-a134-1bf27bb72afb.png)

### Use Case:

A User Can Request a Product with His **userId** from the **OrderService**. If the user is allowed to request the product, his account balance is checked from **AccountService** . If he has sufficient balance, an amount will be deducted from account. After that, **DeliveryService** will deliver the order. **Saga Pattern was implemented with Temporal Orchestrator.**

#### OrderService

- PlaceOrderWorker
- PlaceOrderWorkflow Registered in PlaceOrderWorker(Calls Activities on OrderService, AccountService & DeliveryService)
- PlaceOrderActivities Registered in PlaceOrderWorker

#### AccountService

- AccountServiceWorker
- AccountServiceActivities Registered in AccountServiceWorker

#### AccountService

- AccountServiceWorker
- AccountServiceActivities Registered in AccountServiceWorker





