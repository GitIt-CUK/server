FROM openjdk:11
VOLUME /tmp
ARG JAR_FILE_PATH=./build/libs/gitit-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE_PATH} app.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","gitit-0.0.1-SNAPSHOT.jar"]