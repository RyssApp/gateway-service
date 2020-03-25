FROM gradle AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle clean shadowJar --no-daemon

FROM adoptopenjdk:8-jre-openj9
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/gateway-service.jar
CMD ["java", "-jar", "/app/gateway-service.jar"]