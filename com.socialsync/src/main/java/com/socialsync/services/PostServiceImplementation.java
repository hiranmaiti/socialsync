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

	@Override
	public List<Post> fetchAllPosts() {
		return repo.findAll();
	}
	
	@Override
	public Post getPost(Long id) {
		return repo.findById(id).get();
	}

	@Override
	public void updatePost(Post post) {
		repo.save(post);
	}

}
