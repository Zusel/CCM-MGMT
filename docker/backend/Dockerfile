FROM gradle:latest
WORKDIR /build
COPY . ./

RUN mvn bootJar


FROM openjdk:18-ea-slim
