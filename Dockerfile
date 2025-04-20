FROM openjdk:17
COPY target/blockchain-app.jar blockchain-app.jar
ENTRYPOINT ["java", "-jar", "/blockchain-app.jar"]
