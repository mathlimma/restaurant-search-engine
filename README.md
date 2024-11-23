# Restaurant search engine
Restaurant search engine - Fullstack Technical Assessment
by Matheus de Andrade Lima

## Assumptions

- Lowercase and uppercase don`t matter for string inputs.
  e.g. italian/Italian 

- Restaurants.csv and cuisine.csv files won`t be updated during program execution, then I can cache for better performance.

- Max length for string inputs is 100 characters then I can avoid some vulnerabilities.
  https://en.wikipedia.org/wiki/Improper_input_validation

- If the user does not specify a field, default value will be the less restrictive allowed value for that field.
  e.g. default value for price: 50

- Numeric inputs type is set as Double in case we need to change and adapt to this functionality in the future.

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
