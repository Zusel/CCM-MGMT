FROM gradle:latest as builder
WORKDIR /build
COPY ./backend/ ./

RUN gradle bootJar

FROM openjdk:18-ea-slim
WORKDIR /app
RUN apt update && apt install curl -y

COPY --from=builder /build/build/libs/ccm-mgmt.jar .
CMD ["java", "-jar", "ccm-mgmt.jar"]