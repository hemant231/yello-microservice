/*package com.yello.api.config;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.meanbean.test.BeanTester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class MongoDBConfigParamsTest {
	
    @Autowired
	MongoDBConfigParams mongoDBConfigParams;

	BeanTester beanTester;
	
	@Before
	public void setUp() throws Exception {
		beanTester = new BeanTester();
	}
	
	@Test
	public void test() {
		Assert.assertEquals(mongoDBConfigParams.getDatabase(), "dev_db");
		Assert.assertEquals(mongoDBConfigParams.getHost(), "localhost");
		Assert.assertEquals(mongoDBConfigParams.getPassword(), ""); 
																				
		Assert.assertEquals(mongoDBConfigParams.getPort(), 27017);
		Assert.assertEquals(mongoDBConfigParams.getUsername(), "");
		beanTester.testBean(MongoDBConfigParams.class);
	}

}
*/