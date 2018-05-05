package com.globant;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BlogTest {

	private Blog blog;

	@Before
	public void setUp(){
		this.blog = new Blog();
	}

	@Test
	public void whenTheBlogStartsThenIsEmpty(){
		assertTrue(blog.isEmpty());
	}

	@Test
	public void WhenAddNewPostThenAddAtTheBeginning(){
		Post p = mock(Post.class);
		this.blog.addPost(p);
		assertEquals(p,this.blog.getLastPost());
	}

	@Test
	public void whenTryToDeleteAPostThatNotExistsThenReturnFalse(){
		assertFalse(this.blog.deleteByTitle("non-existing title"));
	}

	@Test
	public void whenTryToDeleteAPostAndExistsThenReturnTrue(){
		String title = "mocked title";
		Post p = mock(Post.class);
		when(p.getTitle()).thenReturn(title);
		this.blog.addPost(p);
		assertTrue(this.blog.deleteByTitle(title));
	}
	@Test
	public void givenEmptyBlogWhenTryToGetTenLatestPostsThenReturnEmptyList(){
		List latestTen = this.blog.getLatestTen();
		assertEquals(0,latestTen.size());
	}

	@Test
	public void givenLessThanTenPostsBlogWhenTryToGetTenLatestPostsThenReturnListWithAll(){
		final int postQty = 5;
		this.addPosts(postQty);
		List latest = this.blog.getLatestTen();
		assertEquals(postQty,latest.size());
	}

	@Test
	public void givenPlentyOfPostsBlogWhenTryToGetTenLatestPostThenReturnListWithLatestTen(){
		final int postQty = 12;
		this.addPosts(postQty);
		List latest = this.blog.getLatestTen();
		assertEquals(10,latest.size());
	}

	private void addPosts(int postQty){
		for( int i = 0; i<postQty; i++){
			Post p = mock(Post.class);
			when(p.getTitle()).thenReturn("title " + i+1);
			this.blog.addPost(p);
		}
	}
}
