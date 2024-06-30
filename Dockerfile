FROM openjdk:17
ARG JAR_FILE=bulid/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]