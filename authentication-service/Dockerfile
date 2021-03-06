FROM openjdk:11
WORKDIR usr/src
ADD ./target/authentication-service.jar authentication-service.jar
ENTRYPOINT ["java","-jar", "authentication-service.jar"]