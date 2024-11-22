# Restaurant search engine
Restaurant search engine - Fullstack Technical Assessment
by Matheus de Andrade Lima

## Assumptions

- Lowercase and uppercase don`t matter for string inputs
- Restaurants.csv and cuisine.csv files won`t be updated during program execution

## Running application

* To run this project you must have the programs below installed on your local machine.
    - java
    - docker

Run the command bellow to install necessary dependencies and images to deploy on docker using docker-compose.

```
sudo bash docker-setup.sh
```
Access: 

- http://localhost:8080/actuator/health to check out application health.
- http://localhost:3000/dashboards to check out grafana dashboards. (use "admin" for user and password)
- http://localhost:16686/search to check out tracing on Jaeger.


To stop you can run
```
sudo docker-compose -f docker-compose.yml down
```

This project benefits from [OpenTelemetry](https://opentelemetry.io/) as a generic way to collect and send spans to tracers.
