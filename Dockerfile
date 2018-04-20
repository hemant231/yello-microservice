FROM java:8

RUN apt-get update

# Install necessary tools
RUN apt-get install -y nano wget dialog net-tools

# Download and Install Nginx
RUN apt-get install -y nginx  

# Remove the default Nginx configuration file
RUN rm -v /etc/nginx/nginx.conf

EXPOSE 8082

ADD target/yello-ms-0.0.1-SNAPSHOT.jar yello-ms-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","yello-ms-0.0.1-SNAPSHOT.jar","--server.port=8086"]  




  
