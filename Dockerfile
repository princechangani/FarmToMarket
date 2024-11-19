FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Use OpenJDK 17 for the runtime stage
FROM openjdk:17-jdk-slim
COPY --from=build /target/FarmToMarket-0.0.1-SNAPSHOT.jar FarmToMarket.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "FarmToMarket.jar"]
