# Build stage
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /config-server
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Run stage
FROM amazoncorretto:21
WORKDIR /config-server
COPY --from=build /config-server/target/config-server-0.0.1-SNAPSHOT.jar config-server.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "config-server.jar"]