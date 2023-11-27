FROM maven:3.9.5-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY libraries ./libraries
COPY services ./services

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY --from=build /app/services/users/target/users.jar .
CMD ["java", "-jar", "users.jar"]
