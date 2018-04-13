FROM java:8

EXPOSE 8080

ADD target/yello-ms-0.0.1-SNAPSHOT.jar yello-ms-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","yello-ms-0.0.1-SNAPSHOT.jar","--server.port=8080"]  




  