# Build stage
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /cloud-gateway
COPY pom.xml . 
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Run stage
FROM amazoncorretto:21
WORKDIR /cloud-gateway
COPY --from=build /cloud-gateway/target/cloud-gateway-0.0.1-SNAPSHOT.jar cloud-gateway.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "cloud-gateway.jar"]
