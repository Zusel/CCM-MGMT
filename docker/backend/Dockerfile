FROM gradle:latest as builder
WORKDIR /build
COPY ./backend/ ./

RUN gradle bootJar

FROM openjdk:18-ea-slim
WORKDIR /app

COPY --from=builder /build/build/libs/ccm-mgmt.jar .
CMD ["/bin/bash", "java", "-jar", "ccm-mgmt.jar"]