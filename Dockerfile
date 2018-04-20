FROM java:8

RUN apt-get update

# Install necessary tools
RUN apt-get install -y nano wget dialog net-tools

# Download and Install Nginx
RUN apt-get install -y nginx  

# Remove the default Nginx configuration file
RUN rm -v /etc/nginx/nginx.conf

# Copy a configuration file from the current directory
ADD nginx/nginx.conf /etc/nginx/

# Append "daemon off;" to the configuration file
RUN echo "daemon off;" >> /etc/nginx/nginx.conf

# Expose ports
EXPOSE 8082

# Set the default command to execute when creating a new container
CMD service nginx start

ADD target/yello-ms-0.0.1-SNAPSHOT.jar yello-ms-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","yello-ms-0.0.1-SNAPSHOT.jar","--server.port=8086"]  




  
