FROM openjdk:11
WORKDIR usr/src
ADD ./target/mentor-platform-service.jar mentor-platform-service.jar
ENTRYPOINT ["java","-jar", "mentor-platform-service.jar"]