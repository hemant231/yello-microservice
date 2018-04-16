package com.yello.api.service;

import static com.yello.api.constant.Constants.DB_INSERTION_ERROR;
import static com.yello.api.constant.Constants.DB_UPDATE_ERROR;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.yello.api.constant.Constants.STATUS;
import com.yello.api.dao.MongoDbOperation;
import com.yello.api.model.UserInfo;
import com.yello.api.pojo.ApiResponse;
import com.yello.api.util.CommonUtil;

/**
 * This is a Repository. It implements All the method listed in UserdataService
 * 
 * @author kaushal
 * @version 1.0
 * @date 27.03.2018
 */
@Repository
public class UserdataServiceImpl implements UserdataService {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(UserdataServiceImpl.class);

    @Autowired
    MongoDbOperation mongoDbOperation;
    
    // hello world test method
    public String hello() {
        return "Hello Mr. Sherlock Holmes.";
    }
    
    /**
     * Method to Insert Data In User_Info collections.
     * 
     * @param userInfo
     * @return Api response for Insert User.
     */
    @Override
    public ApiResponse insertUserInfo(UserInfo userInfo) {

    	//payload for first name last name there, just have to update userinfo object with creation date
        userInfo.setCreationDate(new Date());
        
        LOGGER.info("Inserting Data to User_Info DB " +userInfo);
        UserInfo responseFrmUserInfo = mongoDbOperation
                .insertUserinfo(userInfo);

        if (null == responseFrmUserInfo) {

            LOGGER.info("Data can not insert, Response from User_Info is null :");
            return CommonUtil.createApiResponseFail(STATUS.ERROR, null,
                    HttpStatus.BAD_REQUEST.value(), DB_INSERTION_ERROR);
        }

        LOGGER.info("Successfully inserted the data ", STATUS.SUCCESS);
        return CommonUtil
                .createApiResponse(STATUS.SUCCESS, responseFrmUserInfo);
    }

    /**
     * Method to Fetch All the Data from User_Info collections.
     * 
     * @return
     */
    @Override
    public ApiResponse fetchAllUserInfo() {

        LOGGER.info("Fetching All the Data from User_Info");
        List<UserInfo> allUserInfo = mongoDbOperation.fetchAllUserInfo();

        if (allUserInfo.isEmpty()) {

            LOGGER.info("There is no Data available ");
            return CommonUtil.createApiResponseFail(STATUS.ERROR, null,
                    HttpStatus.BAD_REQUEST.value(), DB_UPDATE_ERROR);
        }

        LOGGER.info("Successfully fetched the data ", STATUS.SUCCESS);
        return CommonUtil.createApiResponse(STATUS.SUCCESS, allUserInfo);

    }
}
