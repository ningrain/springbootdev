FROM openjdk:8-jdk-alpine

VOLUME /tmp

ADD springbootdev-0.0.1-SNAPSHOT.war app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]