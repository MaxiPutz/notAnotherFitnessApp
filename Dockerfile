FROM alpine/java:17-jdk


RUN apk add --no-cache fish maven

COPY . /app

WORKDIR /app

RUN mvn compile

