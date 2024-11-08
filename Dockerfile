FROM openjdk:21-jdk
WORKDIR /app
COPY Histomap.jar Histomap.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "Histomap.jar"]