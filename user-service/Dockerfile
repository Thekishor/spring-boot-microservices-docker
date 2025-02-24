# Build stages
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /user-service
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

#Run stage
FROM amazoncorretto:21
WORKDIR /user-service
COPY --from=build /user-service/target/user-service-0.0.1-SNAPSHOT.jar user-service.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "user-service.jar"]