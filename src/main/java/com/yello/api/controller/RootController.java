package com.yello.api.controller;

import static com.yello.api.constant.Constants.USER_INFO;
import static com.yello.api.constant.Constants.INSERT_TO;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yello.api.model.UserInfo;
import com.yello.api.model.YelloServiceCallReport;
import com.yello.api.pojo.ApiResponse;
import com.yello.api.service.UserdataService;
import com.yello.api.service.YelloService;

/**
 * This is RootController class consists method, used to handle HTTP request.
 * 
 * @author Kaushal
 * @version 1.0
 * @since 26-03-2018
 */
@RestController
@RequestMapping(value = "/yelloApp/v1")
public class RootController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(RootController.class);

	@Autowired
	YelloService yelloService;
	
	@Autowired
    UserdataService userDataService;
	

	/**
	 * test hello response message.
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String helloWorld() {
        return userDataService.hello();
    }

	/**
	 * test by echo message.
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
	public String echo(@PathVariable String str) {
		return str+" !!!!!!!!!!! ";
	}

	/**
	 * get Time stamp and no of calls service calls.
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/hitCount", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	ApiResponse hitCount() throws IOException {

		LOGGER.info("get Time stamp and no of calls service calls : {}");
		return yelloService.getTimeStumpandNoOfCalls();
	}
	
	/**
     * Create information in collections.
     * Based on insert-to values, insert the data in respective collection. 
     * 
     * @param UserInfo
     * @return Create API response
     * @throws IOException
     */
    @RequestMapping(value = "/{" + INSERT_TO + "}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ApiResponse create(@RequestBody String content,
            @PathVariable(value = INSERT_TO) String insertTo)
            throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        ApiResponse apiResponse = new ApiResponse();

        if (insertTo.equalsIgnoreCase(USER_INFO)) {

            UserInfo userInfo = mapper.readValue(content, UserInfo.class);
            LOGGER.info("Create operation with Request context:/yelloApp/v1 Method: POST : "
                    + userInfo);
            apiResponse = userDataService.insertUserInfo(userInfo);
        }
        return apiResponse;

    }
    
    /**
     * Fetch user information from User_Info collection based on filter criteria.
     * 
     * @param fetchBy
     * @param fetchValue
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/fetch", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ApiResponse fetch() throws IOException {

        LOGGER.info("fetching All  User_Info entry : {}");
        return userDataService.fetchAllUserInfo();
    }

}
