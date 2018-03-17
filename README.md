# Skip The Dishes (Java Challenge)

Backend project (API) for a food delivery application.


## The challenge
- The project is the construction of an API to:
- Allow Authentication;
- Query Products;
- Receive Orders;
- Cancel an Order;
- Get Order Status;
- Store data in a database of his/her choice;


## Model entities

`Customer` - user that places orders

`Cousine` - the cousine type of store

`Product` - list of available products to be ordered by customers

`Order` - create, check status, cancel orders.

`Store` - store that have list of products.


## Entity Diagram
![](entity-diagram.png?raw=true)

## Setup & Run

- `clone`
- execute the file `database.sql` on local installation of MySQL 5.7+ (you can change the server editing the file `application.properties`).
- `mvn spring-boot:run`
- go to `http://localhost:8080/swagger-ui.html#/` to see the available endpoints


## API Docs

Available at: `http://localhost:8080/swagger-ui.html#/`

This project's web services documentation was generated using Spring and [Swagger](https://swagger.io/) integration, the SpringFox project.

If you want to learn more about SpringFox project check [here](https://springfox.github.io/springfox/docs/current/).

## Test endpoints with Postman

Test this application endpoints with Postman:

- [Download it](https://www.getpostman.com/)
- Start it and import `SkipTD_JavaChallange.postman_collection.json` which is a collection of example requests.

## Persistence layer

For this project I used MySQL. Since I am also using an ORM solution (Hibernate) it could be switched to another database solution with no major issues. But, also, have a sample using pure JDBC: `ProductDAOJDBCSampleImpl.java`
