package com.mahesh.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mahesh.blog.entities.Category;
import com.mahesh.blog.entities.Post;
import com.mahesh.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>{
	
	
	// custom methods
	List<Post>findByUser(User user);

	List<Post>findByCategory(Category category);
	
	@Query("select p from Post p where p.title like : key")
	List<Post> searchByTitle(@Param("key") String title);
}
