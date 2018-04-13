package com.yello.api.service;

import com.yello.api.model.UserInfo;
import com.yello.api.pojo.ApiResponse;

/**
 * This is an interface, Define all the methods implemented in
 * UserdataserviceImpl class which acts as a Repository.
 * 
 * @author kaushal
 * @version 1.0
 * @since 27.03.2018
 */
public interface UserdataService {

    /**
     * Method to Insert Data In User_Info collection.
     * 
     * @param userInfo
     * @return
     */
    ApiResponse insertUserInfo(UserInfo userInfo);

    /**
     * Method to Fetch Data In User_Info collections.
     * 
     * @return
     */
    ApiResponse fetchAllUserInfo();
    
    /**
     * Method to return a hello world message
     * 
     * @return
     */
    String hello();
    
    
}
