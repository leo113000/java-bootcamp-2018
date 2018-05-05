package com.globant;

import java.util.*;

public class Blog {
	/**
	 * Collection to be used
	 */
	Deque<Post> posts;

	/**
	 * The constructor initialize the Collection
	 */
	public Blog() {
		this.posts = new ArrayDeque<>();
	}

	/**
	 * @return true if the collection is empty
	 */
	public Boolean isEmpty() {
		return this.posts.isEmpty();
	}

	/**
	 * @param p Post object to be added at the beginning
	 */
	public void addPost(Post p) {
		this.posts.addFirst(p);
	}

	/**
	 * @return the latest post made it (the newer)
	 */
	public Post getLatestPost() {
		return this.posts.getFirst();
	}

	/**
	 * @param s the title of the blog's post to be deleted
	 * @return true if the file was erased
	 */
	public Boolean deleteByTitle(String s) {
		Boolean result = false;
		for (Post p : this.posts) {
			if (p.getTitle().equalsIgnoreCase(s)) {
				this.posts.remove(p);
				result = true;
			}
		}
		return result;
	}

	/**
	 * - Return the latest ten  posts
	 * - If the blog have less than ten posts, return all the posts
	 * - Return an empty ArrayList if there are not any post
	 *
	 * @return List
	 */
	public List getLatestTenPosts() {
		List latestTenPosts = new ArrayList();
		Iterator<Post> itr = this.posts.iterator();
		for (int i = 0; itr.hasNext() && i < 10; i++) {
			latestTenPosts.add(itr.next());
		}
		return latestTenPosts;
	}
}
