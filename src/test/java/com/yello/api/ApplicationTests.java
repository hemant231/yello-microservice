package com.yello.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest (classes = Application.class)
public class ApplicationTests {

	@Test
	public void contextLoads() throws Exception {
		
		//Assert.assertTrue(true);
		//Application.main(new String[] {});
	}
	
	/*@Test
	   public void contextLoads() {
		Application.main(new String[] {});
	   }*/

}