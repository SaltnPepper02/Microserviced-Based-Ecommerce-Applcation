FROM maven:3.9.6 AS build
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} user.jar
ENTRYPOINT ["java", "-jar", "/user.jar"]
EXPOSE 8080

ENV MYSQL_HOST host.docker.internal