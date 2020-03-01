FROM openjdk:8

COPY target/rest-example-1.0-SNAPSHOT.jar rest-example-1.0-SNAPSHOT.jar
CMD java -jar rest-example-1.0-SNAPSHOT.jar
EXPOSE 8080
