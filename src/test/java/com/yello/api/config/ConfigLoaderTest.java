/*package com.yello.api.config;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.yello.api.Application;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

*//**
 * This is ConfigLoaderTest class. It provides all the configuration for Unit
 * testing.
 
 *//*
@RunWith(SpringJUnit4ClassRunner.class)
@IntegrationTest({ "spring.profiles.active=dev","artifact.id=yello-ms", "artifact.version=0.0.1-SNAPSHOT", "environment.nodeName=", "environment.lifecycle=sb" })
@SpringApplicationConfiguration(classes = { Application.class, MongoDBConfigParams.class})
@ComponentScan
@WebAppConfiguration
@EnableAutoConfiguration
public class ConfigLoaderTest {

    @Test
    public void test() {

    }

    *//**
     * Maps an Object into a JSON String. Uses a Jackson ObjectMapper.
     * 
     * @param obj
     *            The Object to map.
     * @return A String of JSON.
     * @throws JsonProcessingException
     *             Thrown if an error occurs while mapping.
     *//*
    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

    *//**
     * Maps a String of JSON into an instance of a Class of type T. Uses a
     * Jackson ObjectMapper.
     * 
     * @param json
     *            A String of JSON.
     * @param clazz
     *            A Class of type T. The mapper will attempt to convert the JSON
     *            into an Object of this Class type.
     * @return An Object of type T.
     * @throws JsonParseException
     *             Thrown if an error occurs while mapping.
     * @throws JsonMappingException
     *             Thrown if an error occurs while mapping.
     * @throws IOException
     *             Thrown if an error occurs while mapping.
     *//*
    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, clazz);
    }

}*/