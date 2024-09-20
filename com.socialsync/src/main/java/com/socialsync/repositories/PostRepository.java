package com.socialsync.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialsync.entities.Post;

public interface PostRepository extends JpaRepository<Post, Long> {


}
