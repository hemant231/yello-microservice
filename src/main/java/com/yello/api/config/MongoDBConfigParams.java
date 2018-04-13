package com.yello.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Defines attributes to connect to MongoDB database (with prefix "mongodb")
 * specified in the application.yml configuration file
 *
 * @author kaushal
 * @version 1.0
 * @since 28.03.2018
 */
@Component
@ConfigurationProperties(prefix = "mongodb")
public class MongoDBConfigParams {

    private String host;
    private int port;
    private String database;
    private String username;
    private String password;
    private String authenticationDB;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthenticationDB() {
        return authenticationDB;
    }

    public void setAuthenticationDB(String authenticationDB) {
        this.authenticationDB = authenticationDB;
    }
}