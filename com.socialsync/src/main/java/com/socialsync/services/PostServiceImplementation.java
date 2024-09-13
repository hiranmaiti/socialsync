package com.socialsync.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialsync.entities.Post;
import com.socialsync.repositories.PostRepository;

@Service
public class PostServiceImplementation implements PostService{
	
	@Autowired
	PostRepository repo;
	
	@Override
	public void createPost(Post post) {
		// TODO Auto-generated method stub
		
		repo.save(post);
		
	}

	@Override
	public List<Post> getAllPosts() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

}
