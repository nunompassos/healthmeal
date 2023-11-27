FROM maven:3.9.5-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY libraries ./libraries
COPY services ./services

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY --from=build /app/services/products/target/products.jar .
CMD ["java", "-jar", "products.jar"]

#COPY products_entrypoint.sh /usr/local/bin/entrypoint.sh
#RUN chmod +x /usr/local/bin/entrypoint.sh

#Start application
#WORKDIR /usr/src/mymaven
#ENTRYPOINT ["/usr/local/bin/entrypoint.sh"]
#CMD ["bash"]
