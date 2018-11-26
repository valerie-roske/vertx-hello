FROM node:10.13.0-alpine AS node-build
WORKDIR /app
COPY web-src/ web-src
WORKDIR /app/web-src
RUN npm install
RUN npm run build

FROM maven:3.5-jdk-8-alpine AS java-build
WORKDIR /app
COPY src/ src
COPY --from=node-build /app/web-src/build web-src/build
COPY pom.xml .
RUN mvn clean package -DskipTests

FROM openjdk:8-jdk AS java-run
EXPOSE 3001
WORKDIR /app
COPY --from=java-build /app/target/vertx-hello-1.0.0-SNAPSHOT-fat.jar .
CMD java -jar vertx-hello-1.0.0-SNAPSHOT-fat.jar



