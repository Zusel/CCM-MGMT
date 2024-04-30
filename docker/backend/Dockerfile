FROM gradle:latest as builder
WORKDIR /build
COPY ./backend/ ./

RUN gradle bootJar

FROM openjdk:18-ea-slim
WORKDIR /app

COPY --from=builder /build/build/libs/ccm-mgmt.jar .
EXPOSE 8080
CMD ["java", "-jar", "ccm-mgmt.jar"]