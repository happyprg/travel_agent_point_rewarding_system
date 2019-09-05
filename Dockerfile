FROM openjdk:8-jdk-alpine

RUN apk add --update \
    curl \
    wget \
    && rm -rf /var/cache/apk/*

EXPOSE 8080
ADD /build/libs/triple_point-0.0.1-SNAPSHOT.jar triple-point.jar
ENTRYPOINT ["java", "-jar", "triple-point.jar"]