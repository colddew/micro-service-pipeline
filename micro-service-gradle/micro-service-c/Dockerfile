FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
EXPOSE 9999
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","/app.jar"]