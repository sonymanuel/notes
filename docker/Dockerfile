FROM openjdk:8-jdk-alpine

RUN mkdir /app
COPY notes*.jar /app/
EXPOSE 8080

WORKDIR /app

ENV mysql_port=3306
ENV mysql_host=localhost

CMD java -jar  notes-0.0.1-SNAPSHOT.jar --spring.datasource.url=jdbc:mysql://${mysql_host}:${mysql_port}/notes_app?useSSL=false

