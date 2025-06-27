
FROM openjdk:24-jdk-slim

WORKDIR /app

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar


EXPOSE 8080

# Comando que roda a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]