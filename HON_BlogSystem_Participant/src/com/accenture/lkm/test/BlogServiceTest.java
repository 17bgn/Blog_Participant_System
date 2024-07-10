package com.accenture.lkm.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.accenture.lkm.bean.BlogBean;
import com.accenture.lkm.exception.MaxAllowedEntriesReachedException;
import com.accenture.lkm.service.BlogServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:WebContent/WEB-INF/root-config.xml"})
public class BlogServiceTest {
	
	@Autowired
	private BlogServiceImpl blogService;
	
	/**
	   To-Do Item 1.17 : This method tests the addBlog functionality in BlogServiceImpl ensuring that a blog entry is added only
	   when the number of existing posts for the associated email is less than the defined limit (5 entries in our case).
	   
	   TODO: Implement the testAddBlogWithLessThanFivePosts_Positive method:
	     - It should create a new instance of BlogBean and set its properties (title, content, email, themes) with dummy data.
	     - It should invoke the addblog method of blogDAOWrapper, passing the BlogBean object.
	     - It should verify that a new blog entry has been successfully inserted into the database table.
	 */
	@Test
	public void testAddBlogWithLessThanFivePosts_Positive() throws Exception {
		
		 BlogBean blogBean = new BlogBean();
		  blogBean.setTitle("DummyTitle");
		  blogBean.setContent("DummyContent");
		  //with the given E-Mail Id in my Database less than 5 blogs are present.
		  blogBean.setEmail("kutty66@gmail.com");
		  blogBean.setThemes("dummytheme");
		 int id =  blogService.addBlog(blogBean);
		 Assert.assertNotNull(id);
		
	}
	/**
	   To-Do Item 1.18: This method test the addBlog functionality in BlogServiceImpl to ensure that a blog entry is not added
	   when the number of existing posts for the associated email reaches the defined limit (5 entries in our case).
	  
	   TODO: Implement the testAddBlogWithLessThanFivePosts_Negative method:
	    - It should add blog entries for the specified email until the maximum allowed posts (5 in our case) is reached.
	    - It should attempt to add an additional blog entry for the same email.
	    - It ensure that adding the additional blog entry throws a MaxAllowedEntriesReachedException with the message "User cannot enter more than 5 Blog entries".
	 */
	@Test
	public void testAddBlogWithLessThanFivePosts_Negative() throws Exception {
		
		BlogBean blogBean = new BlogBean();
		  blogBean.setTitle("DummyTitle");
		  blogBean.setContent("DummyContent");
		  //with the given E-Mail Id in my Database already 5 blogs are present.
		  blogBean.setEmail("kutty66@gmail.com");
		  blogBean.setThemes("dummytheme");
		  MaxAllowedEntriesReachedException exception = Assertions.assertThrows(MaxAllowedEntriesReachedException.class,() ->  blogService.addBlog(blogBean));
		 Assert.assertEquals(exception.getMessage(),"User cannot enter more than 5 Blog entries");
	}

	/**
	   To-Do Item 1.19: This method test getAllPosts functionality in BlogServiceImpl for retrieving all post associated with a specified email.
	  
	   TODO: Implement the testGetAllPosts method:
	    - It should call the getAllPosts method of blogService with a specified email.
	    - It should assert that the result obtained is not null.
	 */
	@Test
	public void testGetAllPosts() throws Exception {
		
		List<BlogBean> blogBeanList = blogService.getAllPosts("bgnagendra66@gmail.com");
		Assert.assertNotNull(blogBeanList);
	}
	    
}
