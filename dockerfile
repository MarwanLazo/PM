# FROM openjdk:jdk-11.0.11_9-alpine

FROM adoptopenjdk/openjdk11

COPY target/champion-0.0.1-SNAPSHOT.jar /champion.jar

CMD ["java", "-jar", "/champion.jar"]


#,"--MYSQL_HOST=app-db"
# $ docker build -t { your image name} .  ====> duild image 
# $ docker run -d -p 8080:8080 {your image name 'line line 12 ' } --name {container name}