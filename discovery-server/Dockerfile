# Build stages
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /discovery-server
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

#Run stage
FROM amazoncorretto:21
WORKDIR /discovery-server
COPY --from=build /discovery-server/target/discovery-server-0.0.1-SNAPSHOT.jar discovery-server.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "discovery-server.jar"]