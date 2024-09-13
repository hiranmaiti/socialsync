package com.socialsync.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialsync.entities.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
