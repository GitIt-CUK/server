FROM openjdk:11
VOLUME /tmp
ARG JAR_PATH=./build/libs/
COPY ${JAR_PATH}gitit-0.0.1-SNAPSHOT.jar ${JAR_PATH}gitit-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "build/libs/gitit-0.0.1-SNAPSHOT.jar"]