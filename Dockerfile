FROM amazoncorretto:17-al2-jdk
VOLUME /tmp
ADD target/bank-api-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
EXPOSE 8080