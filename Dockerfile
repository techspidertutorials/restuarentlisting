FROM eclipse-temurin:21-jre
WORKDIR /app

COPY target/*.jar /app/restuarant.jar

# Run the service
ENTRYPOINT ["java", "-jar", "/app/restuarant.jar"]