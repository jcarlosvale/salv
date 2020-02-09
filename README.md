# Simulating the retrieve and inserting Orders API
by João Carlos (https://www.linkedin.com/in/joaocarlosvale/)

This project consists of a REST API Java application that can be used for:
* Retrieving all Orders present
* Inserting a new Order

## Technologies used:
* Java
* Spring
* Spring Boot
* H2 database engine (https://www.h2database.com/html/main.html): embedded relational database
* Maven

## Enpoints

``` 
/orders/new  GET method
``` 
- retrieve all orders in JSON format (OrdersResponse JSON)

``` 
/order/new  POST method
``` 
- body input: OrderRequest (JSON format)
- returns the inserted order in JSON format (OrderDto JSON)

##JSON formats

### OrdersResponse (2 Orders)
```json
{
    "orders": [
        {
            "id": 1,
            "description": "some order 1",
            "date": "2019-08-16",
            "totalValue": 3.00,
            "client": {
                "id": 3,
                "name": "some name 3"
            },
            "orderDetails": [
                {
                    "id": 2,
                    "product": {
                        "id": 2,
                        "description": "some product 2",
                        "price": 2.00
                    },
                    "quantity": 1,
                    "value": 2.00
                },
                {
                    "id": 1,
                    "product": {
                        "id": 1,
                        "description": "some product 1",
                        "price": 1.00
                    },
                    "quantity": 1,
                    "value": 1.00
                }
            ]
        },
        {
            "id": 2,
            "description": "some order 2",
            "date": "2019-09-09",
            "totalValue": 2.00,
            "client": {
                "id": 2,
                "name": "some name 2"
            },
            "orderDetails": [
                {
                    "id": 3,
                    "product": {
                        "id": 2,
                        "description": "some product 2",
                        "price": 2.00
                    },
                    "quantity": 1,
                    "value": 2.00
                }
            ]
        }
    ]
}
```  

### OrderRequest example (used to create an order)
```json
{
  "description":"some order to create",
  "clientId":1,
  "products":[
    {
      "id":2,
      "quantity":2
    },
    {
      "id":1,
      "quantity":1
    }]
}
```  
### OrderDto (Response of a created Order)
```json
{
    "id": 6,
    "description": "descripton",
    "date": "2020-02-09",
    "totalValue": 5.00,
    "client": {
        "id": 1,
        "name": "some name 1"
    },
    "orderDetails": [
        {
            "id": 10,
            "product": {
                "id": 1,
                "description": "some product 1",
                "price": 1.00
            },
            "quantity": 1,
            "value": 1.00
        },
        {
            "id": 11,
            "product": {
                "id": 2,
                "description": "some product 2",
                "price": 2.00
            },
            "quantity": 2,
            "value": 4.00
        }
    ]
}
```  


## Commands:

To run:

    mvn spring-boot:run
    
To run tests:

    mvn test
