FROM eclipse-temurin:21-jre
COPY target/maintenance-api-0.0.1-SNAPSHOT.jar arquivo.jar
ENTRYPOINT ["java", "-jar", "arquivo.jar"]