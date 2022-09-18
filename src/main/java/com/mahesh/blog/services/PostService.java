package com.mahesh.blog.services;

import java.util.List;

import com.mahesh.blog.entities.Post;
import com.mahesh.blog.payloads.PostDto;
import com.mahesh.blog.payloads.PostResponse;

public interface PostService {
	
	// create Post
	
	 PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	 
	 PostDto updatePost(PostDto postDto, Integer postId);
	 
	 
	 void deletePost(Integer postId);
	 
	 PostResponse getAllPost( Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	 
	 PostDto getPostById(Integer postId);
	 
	 //get all posts by category
	 
	 List<PostDto> getPostsByCategory(Integer categoryId);
	 
	 // get posts by user
	 
	 List<PostDto> getPostsByUser(Integer userId);
	 
	 
	 List<PostDto> searchPosts(String keyword);

}
