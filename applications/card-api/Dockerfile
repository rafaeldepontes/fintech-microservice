#Build step
FROM maven:4.0.0-rc-4-amazoncorretto-21 as build
WORKDIR /app
COPY . .
RUN mvn clean package

# Run step
FROM openjdk:21 as run
WORKDIR /app
COPY --from=build ./app/target/*.jar ./app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
