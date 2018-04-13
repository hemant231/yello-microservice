package com.yello.api.service;

import com.yello.api.model.YelloServiceCallReport;
import com.yello.api.pojo.ApiResponse;

/**
 * This is an interface, Define all the methods implemented in
 * YelloServiceImpl class which acts as a Repository.
 * 
 * @author Kaushal
 * @version 1.0
 * @since 27-03-2018
 */
public interface YelloService {

    /**
     * Method to get Time stamp and no of calls to the service.
     * 
     * @return
     */

	ApiResponse getTimeStumpandNoOfCalls();
    
}
