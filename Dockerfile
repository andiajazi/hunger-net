FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .

RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline -B

COPY src ./src

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENV SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/hungernet \
    SPRING_DATASOURCE_USERNAME=postgres \
    SPRING_DATASOURCE_PASSWORD=mysecretpassword \
    SPRING_JPA_HIBERNATE_DDL_AUTO=update \
    JWT_SECRET=MySuperSecretKeyForJWT \
    SERVER_PORT=8080

ENTRYPOINT ["java", "-jar", "app.jar"]