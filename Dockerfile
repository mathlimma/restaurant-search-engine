FROM openjdk:17-jdk-slim-buster

ARG JAR_FILE=build/libs/*-SNAPSHOT.jar
COPY ${JAR_FILE} /app/bin/app.jar

COPY opentelemetry-javaagent.jar /app/bin

CMD java -Dotel.traces.exporter=jaeger \
         -Dotel.exporter.jaeger.endpoint=http://jaeger:14250/ \
         -Dotel.service.name=restaurant-search-engine \
         -Dapplication.home=/app/bin/ \
         -Dapplication.name=restaurant-search-engine \
         -javaagent:/app/bin/opentelemetry-javaagent.jar \
         -Dspring.profiles.active=prod \
         -jar \
         /app/bin/app.jar