package com.yello.api.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.yello.api.constant.Constants.STATUS;
import com.yello.api.model.YelloServiceCallReport;
import com.yello.api.pojo.ApiResponse;
import com.yello.api.util.CommonUtil;

/**
 * This is a Repository. It implements All the method listed in YelloService.
 * 
 * @author Kaushal
 * @version 1.0
 * @since 27-03-2018
 */
@Repository
public class YelloServiceImpl implements YelloService {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(YelloServiceImpl.class);

    private static int calls=1;
    
    /**
     * Method to get Time stamp and no of calls to the service.
     * @return
     */
    @Override
    public ApiResponse getTimeStumpandNoOfCalls() {
        
        LOGGER.info("Fetching  Data");
        
        YelloServiceCallReport yelloServiceCallReport = new YelloServiceCallReport();
    	yelloServiceCallReport.setTimeStamp(new Date());
        yelloServiceCallReport.setCalls(calls);
    	calls++;
    	LOGGER.info("Successfully ", STATUS.SUCCESS);
        return CommonUtil.createApiResponse(STATUS.SUCCESS, yelloServiceCallReport);
    	
    }

}
