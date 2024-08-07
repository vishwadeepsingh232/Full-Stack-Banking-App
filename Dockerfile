From maven:3.8.3-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests
From openjdk:17-ea-18-jdk-slim
COPY --from=build /target/DemoBank_v1-0.0.1-SNAPSHOT.jar DemoBank_v1.jar
Expose 8080
ENTRYPOINT ["java","-jar","DemoBank_v1"]
