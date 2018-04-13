package com.yello.api.dao;

import static com.yello.api.constant.Constants.USER_INFO_COLLECTION;
import static com.yello.api.constant.Constants.FIRST_NAME;
import static com.yello.api.constant.Constants.LAST_NAME;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.yello.api.model.UserInfo;

/**
 * Implementation class of service interface. Its is a DAO layer provides the
 * methods for communicating to DB.
 * 
 * @author kaushal
 * @version 1.0
 * @since 27.03.2018
 */
@Service
public class MongoDbOperation {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(MongoDbOperation.class);

    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * Method to Insert Data In User_Info collection.
     * 
     * @param userInfo
     * @return
     */
    public UserInfo insertUserinfo(UserInfo userInfo) {

        LOGGER.info("Insert in collection " + USER_INFO_COLLECTION);
       try {
            mongoTemplate.save(userInfo, USER_INFO_COLLECTION);
            return userInfo;
        } catch (Exception e) {
            LOGGER.info("Fail to insert", e);
            return null;
        }

    }

    /**
     * Method to fetch List of UserInfo from User_Info collections.
     * 
     * @return
     */

    public List<UserInfo> fetchAllUserInfo() {

        LOGGER.info("Fetch all data from collection " + USER_INFO_COLLECTION);
        try {
            return mongoTemplate.findAll(UserInfo.class, USER_INFO_COLLECTION);
        } catch (Exception e) {

            LOGGER.info("Fail to fetchAll UserInfo", e);
            return null;
        }
    }
    
    /**
     * Method to fetch from User_Info collections based on firstname, lastname.
     * 
     * @param firstName
     * @param lastName
     * @return
     */
    public UserInfo fetchFirstLastName(String firstName, String lastName) {

        LOGGER.info("Fetch data by First and last name from collection "
                + USER_INFO_COLLECTION);
        Query query;
        query = new Query().addCriteria(Criteria.where(FIRST_NAME)
                .is(firstName).and(LAST_NAME).is(lastName));
        try {
            return mongoTemplate.findOne(query, UserInfo.class,
                    USER_INFO_COLLECTION);
        } catch (Exception e) {
            LOGGER.info("Fail to fetch by first , last Name ", e);
            return null;
        }

    }
}
