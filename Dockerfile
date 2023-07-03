FROM adoptopenjdk/openjdk11:latest
ARG JAR_FILE=target/*.jar
COPY ./target/kitchen-0.0.1-SNAPSHOT.jar app.jar
ENV JAVA_OPTS="-Xmx1024m -Xms512m"
ENTRYPOINT ["java", "-jar", "/app.jar"]