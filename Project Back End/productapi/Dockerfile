FROM maven:3.9.6 AS build
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} productapi.jar
ENTRYPOINT ["java", "-jar", "/productapi.jar"]
EXPOSE 8001

ENV MYSQL_HOST host.docker.internal