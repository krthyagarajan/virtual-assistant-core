FROM openjdk:21-jdk

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 9001

ENTRYPOINT ["java", "-jar", "app.jar"]