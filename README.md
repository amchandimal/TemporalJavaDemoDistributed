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

A User Can Request a Product with His **userId** from the **OrderService**. If the user is allowed to request the product, his account balance is checked from **AccountService** . If he has sufficient balance, an amount will be deducted from account. After that, **DeliveryService** will deliver the order. **Saga Pattern was implemented with Temporal Orchestrator.**  Please Refer the following diagram for clarification.

![image](https://user-images.githubusercontent.com/20762013/157870530-e9ca640a-cf80-4671-9564-264bf7c969da.png)

#### OrderService

- PlaceOrderWorker
- PlaceOrderWorkflow Registered in PlaceOrderWorker(Calls Activities on OrderService, AccountService & DeliveryService)
- PlaceOrderActivities Registered in PlaceOrderWorker

#### AccountService

- AccountServiceWorker
- AccountServiceActivities Registered in AccountServiceWorker

#### DeliveryService

- DeliveryServiceWorker
- DeliveryServiceActivities Registered in DeliveryServiceWorker

## How To Run:

### Starting Temporal Server

The following steps will run a local instance of the Temporal Server using docker-compose. You need to have docker-compose in your environment.
```
git clone https://github.com/temporalio/docker-compose.git
cd  docker-compose
docker-compose up
```

After the Server has started, you can open the Temporal Web UI at http://localhost:8088

### Running Microservices

Every Microservice is Spring Web Application. You can open it with your favorite IDE (e.g: IntelliJ Idea, VS Code) and run each service as usual Spring Boot Application.
Try http://localhost:8080/order/sync/<CustomerID> (e.g:http://localhost:8080/order/sync/Chandimal) for start the Workflow Synchronously. Similarly http://localhost:8080/order/async/<CustomerID> will start the Workflow Asynchronously.

Following Customers with relevant balances are Hard Coded for demonstrate the Behaviour.

- Chandimal - 100
- Alex - 120
- Kevin - 50
- Joe - 500

At following conditions Application will throw RunTime Exceptions. But with Saga pattern each case is compensated.
  
* If (userId.length() % 2 == 0), a RunTimeException was thrown at AccountService. (For Alex by default)
* If (userId.length() <= 3), a RunTimeException was thrown at DeliveryService. (For Joe by default)
                           
For More Information Regarding Temporal, Refer the Temporal Official Documentation at https://docs.temporal.io
