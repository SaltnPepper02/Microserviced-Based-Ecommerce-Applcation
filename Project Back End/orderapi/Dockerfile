FROM maven:3.9.6 AS build
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} orderapi.jar
ENTRYPOINT ["java", "-jar", "/orderapi.jar"]
EXPOSE 8002

ENV MYSQL_HOST host.docker.internal