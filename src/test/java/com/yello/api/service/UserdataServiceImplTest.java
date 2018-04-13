package com.yello.api.service;


import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.yello.api.constant.Constants.STATUS;
import com.yello.api.dao.MongoDbOperation;
import com.yello.api.model.UserInfo;
import com.yello.api.pojo.ApiResponse;

public class UserdataServiceImplTest {
	
	@InjectMocks
    UserdataServiceImpl userdataServiceImpl;
	
	@Mock
    MongoDbOperation mongoDbOperation;
	
	UserInfo user;
	List<UserInfo> list;
    
	@Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        user = new UserInfo();
        user.setFirstName("Fname");
        user.setLastName("Lname");
        user.setCreationDate(new Date());
        list = new ArrayList<UserInfo>();
        list.add(user);
    }

	@Test
    public void testInsertUserInfo() {

        ApiResponse response;
        user = new UserInfo();
        
        user.setFirstName("kaushal");
        user.setLastName("singh");
        user.setCreationDate(new Date());

        when(mongoDbOperation.insertUserinfo(any(UserInfo.class))).thenReturn(
                user);
        
        response = userdataServiceImpl.insertUserInfo(user);

        Assert.assertNotNull("failure - expected not null", response.getData());
        Assert.assertEquals(((UserInfo) response.getData()).getFirstName(),
                user.getFirstName());
        Assert.assertEquals(response.getStatus(),STATUS.SUCCESS.toString());

        when(mongoDbOperation.insertUserinfo(any(UserInfo.class))).thenReturn(
                null);
        response = userdataServiceImpl.insertUserInfo(user);
        Assert.assertNull("failure - expected null", response.getData());
        Assert.assertEquals(response.getStatus(),STATUS.ERROR.toString());

    }
	
	@Test
    public void testFetchAllUserInfo() {

        ApiResponse response;
        when(mongoDbOperation.fetchAllUserInfo()).thenReturn(list);
        response = userdataServiceImpl.fetchAllUserInfo();
        Assert.assertNotNull("failure - expected not null", response.getData());
        Assert.assertEquals(response.getStatus(),
                STATUS.SUCCESS.toString());
        // clearing the list
        list.clear();
        when(mongoDbOperation.fetchAllUserInfo()).thenReturn(list);
        response = userdataServiceImpl.fetchAllUserInfo();
        Assert.assertNull("failure - expected null", response.getData());
        Assert.assertEquals(response.getStatus(),
                STATUS.ERROR.toString());

    }

}
