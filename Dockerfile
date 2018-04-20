FROM java:8

FROM nginx:1:13

COPY --from=node /app/dist/ /user/share/nginx/html

COPY ./nginx-custom.conf /etc/nginx/conf.d/default.conf

EXPOSE 8082

ADD target/yello-ms-0.0.1-SNAPSHOT.jar yello-ms-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","yello-ms-0.0.1-SNAPSHOT.jar","--server.port=8082"]  




  
