package com.socialsync.services;

import java.util.List;

import com.socialsync.entities.Post;

public interface PostService {
	
void createPost(Post post);
	
	List<Post> getAllPosts();

	Post getPost(Long id);

	void updatePost(Post post);

	List<Post> fetchAllPosts();

}
