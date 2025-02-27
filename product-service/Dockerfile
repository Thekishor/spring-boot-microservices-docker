# Build stage
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /product-service
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

#Run stage
FROM amazoncorretto:21
WORKDIR /product-service
COPY --from=build /product-service/target/*.jar product-service.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "product-service.jar"]