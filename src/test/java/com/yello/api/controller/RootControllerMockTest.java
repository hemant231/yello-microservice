package com.yello.api.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import com.yello.api.model.UserInfo;
import com.yello.api.model.YelloServiceCallReport;
import com.yello.api.pojo.ApiResponse;
import com.yello.api.service.UserdataService;
import com.yello.api.service.YelloService;
import com.yello.api.util.CommonUtil;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yello.api.constant.Constants.*;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.springframework.http.MediaType;

@RunWith(SpringJUnit4ClassRunner.class)
public class RootControllerMockTest{
	

    private MockMvc mockMvc;

    /*@Mock
    private HelloService helloService;
*/
    
    @Mock
    private UserdataService mockUserdataService;
    
    @Mock
    private YelloService mockYelloService;
    
    /*@InjectMocks
    private HelloResource helloResource;*/
    
    @InjectMocks
    private RootController rootController;
    
	UserInfo user;
    List<UserInfo> list;
    
    YelloServiceCallReport yelloServiceCallReport;
	
	 @Before
	    public void setUp() throws Exception {
	        mockMvc = MockMvcBuilders.standaloneSetup(rootController)
	                .build();
	        
	        // Prepare UserInfo test Data
	        user = new UserInfo();
	        user.setFirstName("Fname");
	        user.setLastName("Lname");
	        user.setCreationDate(new Date());
	        list = new ArrayList<UserInfo>();
	        list.add(user);
	        
	        // Prepare YelloServiceCallReport data
	        YelloServiceCallReport yelloServiceCallReport = new YelloServiceCallReport();
	    	yelloServiceCallReport.setTimeStamp(new Date());
	        yelloServiceCallReport.setCalls(2);
	        
	    }
	 
	 @Test
	    public void testEchoMessage() throws Exception {
	        String uri = "/yelloApp/v1/echo/message";
	        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(uri))
	                .andReturn();
	        Assert.assertEquals(result.getResponse().getContentAsString(),
	                "message");
	        Assert.assertEquals(result.getResponse().getStatus(), 200);
	    }
	 
	 @Test
	    public void testHelloWorld() throws Exception {

	        when(mockUserdataService.hello()).thenReturn("hello");

	        mockMvc.perform(get("/yelloApp/v1/hello"))
	                .andExpect(status().isOk())
	                .andExpect(content().string("hello"));

	        verify(mockUserdataService).hello();
	    }
	 
	 @Test
	    public void testHitCount() throws Exception {

		    ApiResponse entity = CommonUtil.createApiResponse(STATUS.SUCCESS, yelloServiceCallReport);
		    when(mockYelloService.getTimeStumpandNoOfCalls()).thenReturn(entity);

		    String uri = "/yelloApp/v1/hitCount";
		    String inputJson = mapToJson(entity.getData());
	        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(uri)
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .accept(MediaType.APPLICATION_JSON).content(inputJson))
	                .andReturn();
	        String content = result.getResponse().getContentAsString();
	        int status = result.getResponse().getStatus();
	        verify(mockYelloService, times(1)).getTimeStumpandNoOfCalls();
	        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
	        Assert.assertTrue(
	                "failure - expected HTTP response body to have a value",
	                content.trim().length() > 0);
	        ApiResponse createdEntity = mapFromJson(content,
	                ApiResponse.class);
	        Assert.assertNotNull("failure - expected entity not null",
	                createdEntity);
	    }
	 
	 
	 @Test
	    public void testCreateUserInfo() throws Exception {

	        ApiResponse entity = CommonUtil.createApiResponse(STATUS.SUCCESS, user);
	        when(mockUserdataService.insertUserInfo(any(UserInfo.class))).thenReturn(entity);
	        
	        String uri = "/yelloApp/v1/UserInfo";
	        String inputJson = mapToJson(entity.getData());
	        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri)
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .accept(MediaType.APPLICATION_JSON).content(inputJson))
	                .andReturn();
	        String content = result.getResponse().getContentAsString();
	        int status = result.getResponse().getStatus();
	        verify(mockUserdataService, times(1)).insertUserInfo(
	                any(UserInfo.class));
	        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
	        Assert.assertTrue(
	                "failure - expected HTTP response body to have a value",
	                content.trim().length() > 0);
	        ApiResponse createdEntity = mapFromJson(content,
	                ApiResponse.class);
	        Assert.assertNotNull("failure - expected entity not null",
	                createdEntity);
	    }
	 
	 @Test
	    public void testFetchAllUserInfo() throws Exception {

	        ApiResponse entity = CommonUtil.createApiResponse(STATUS.SUCCESS, list);
	        when(mockUserdataService.fetchAllUserInfo()).thenReturn(entity);
	        String uri = "/yelloApp/v1/fetch";
	        MvcResult result = mockMvc.perform(
	                MockMvcRequestBuilders.get(uri).accept(
	                        MediaType.APPLICATION_JSON)).andReturn();
	        String content = result.getResponse().getContentAsString();
	        int status = result.getResponse().getStatus();
	        verify(mockUserdataService, times(1)).fetchAllUserInfo();
	        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
	        Assert.assertTrue(
	                "failure - expected HTTP response body to have a value",
	                content.trim().length() > 0);
	    }
	 
	 
	 // -------------
	 
	 /**
	     * Maps an Object into a JSON String. Uses a Jackson ObjectMapper.
	     * 
	     * @param obj
	     *            The Object to map.
	     * @return A String of JSON.
	     * @throws JsonProcessingException
	     *             Thrown if an error occurs while mapping.
	     */
	    protected String mapToJson(Object obj) throws JsonProcessingException {
	        ObjectMapper mapper = new ObjectMapper();
	        return mapper.writeValueAsString(obj);
	    }
	 
	    /**
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
	     */
	    protected <T> T mapFromJson(String json, Class<T> clazz)
	            throws JsonParseException, JsonMappingException, IOException {
	        ObjectMapper mapper = new ObjectMapper();
	        return mapper.readValue(json, clazz);
	    }
	 
	 /*
	    @Test
	    public void testHelloWorld() throws Exception {

	        when(mockUserdataService.hello()).thenReturn("hello");

	        mockMvc.perform(get("/hello"))
	                .andExpect(status().isOk())
	                .andExpect(content().string("hello"));

	        verify(mockUserdataService).hello();*/
	        
	        
	        
	        
	       /* String uri = "/yelloApp/v1/echo/message";
	        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri))
	                .andReturn();
	        assertEquals(result.getResponse().getContentAsString(),
	                "message");
	        assertEquals(result.getResponse().getStatus(), 200);*/
	  //  }
}




















/**
 * This is RootControllerMocktest which does the unit test for RootController
 * request.
 * 
 * @author Kaushal
 * @version 1.0
 * @since 09-04-2018
 */
/*@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class RootControllerMockTest extends ConfigLoaderTest {
	
	@Mock
    CommonUtil commonUtil;

    @Mock
    private UserdataService mockUserdataService;

    @InjectMocks
    private RootController rootController;

    MockMvc mvc;

    UserInfo user;
    List<UserInfo> list;
    
    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        this.mvc = MockMvcBuilders.standaloneSetup(rootController).build();

        // Prepare UserInfo test Data
        user = new UserInfo();
        user.setFirstName("Fname");
        user.setLastName("Lname");
        user.setCreationDate(new Date());
        list = new ArrayList<UserInfo>();
        // Finally, have a list as entity
        list.add(user);
    }
    
    @Test
    public void testEchoMessage() throws Exception {
        String uri = "/yelloApp/v1/echo/message";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri))
                .andReturn();
        assertEquals(result.getResponse().getContentAsString(),
                "message");
        assertEquals(result.getResponse().getStatus(), 200);
    }
    
    @SuppressWarnings("deprecation")
	@Test
    public void testCreate() throws Exception {

        ApiResponse entity = CommonUtil.createApiResponse(STATUS.SUCCESS, user);
        when(mockUserdataService.insertUserInfo(any(UserInfo.class)))
                .thenReturn(entity);
        String uri = "/yelloApp/v1/UserInfo";
        
        String inputJson = super.mapToJson(entity.getData());
        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).content(inputJson))
                .andReturn();
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();
        verify(mockUserdataService, times(1)).insertUserInfo(
                any(UserInfo.class));
        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);
        
        ApiResponse createdEntity = super.mapFromJson(content,
                ApiResponse.class);
        Assert.assertNotNull("failure - expected entity not null",
                createdEntity);
    }
    
    @SuppressWarnings("deprecation")
	@Test
    public void testFetch() throws Exception {

        ApiResponse entity = CommonUtil.createApiResponse(STATUS.SUCCESS, list);
        when(mockUserdataService.fetchAllUserInfo()).thenReturn(entity);
        String uri = "/yelloApp/v1/fetch";
        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.get(uri).accept(
                        MediaType.APPLICATION_JSON)).andReturn();
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();
        verify(mockUserdataService, times(1)).fetchAllUserInfo();
        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);
    }
*/

//}
