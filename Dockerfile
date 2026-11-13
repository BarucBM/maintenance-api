FROM eclipse-temurin:21-jdk AS build
COPY pom.xml pom.xml
COPY src /src
COPY .mvn .mvn
COPY mvnw mvnw
RUN ./mvnw package -DskipTests

FROM eclipse-temurin:21-jre
COPY --from=build /target/maintenance-api-*.jar arquivo.jar
ENTRYPOINT ["java", "-jar", "arquivo.jar"]