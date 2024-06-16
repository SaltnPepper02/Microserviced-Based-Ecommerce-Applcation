FROM maven:3.9.6 AS build
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} cartapi.jar
ENTRYPOINT ["java", "-jar", "/cartapi.jar"]
EXPOSE 8005

ENV MYSQL_HOST host.docker.internal