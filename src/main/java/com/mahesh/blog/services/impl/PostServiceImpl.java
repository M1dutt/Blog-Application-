package com.mahesh.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;
import com.mahesh.blog.entities.Category;
import com.mahesh.blog.entities.Post;
import com.mahesh.blog.entities.User;
import com.mahesh.blog.exceptions.ResourceNotFoundException;
import com.mahesh.blog.payloads.PostDto;
import com.mahesh.blog.payloads.PostResponse;
import com.mahesh.blog.repositories.CategoryRepo;
import com.mahesh.blog.repositories.PostRepo;
import com.mahesh.blog.repositories.UserRepo;
import com.mahesh.blog.services.PostService;

import net.bytebuddy.asm.Advice.This;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		Category category = this.categoryRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddeddate(new Date());

		post.setUser(user);
		post.setCategory(category);

		Post savedPost = this.postRepo.save(post);
		return this.modelMapper.map(savedPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {

		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));

		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		Post updatedPost= this.postRepo.save(post);
		return this.modelMapper.map(updatedPost, PostDto.class);
	
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));
		
		this.postRepo.delete(post);

		
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
//		
//		Sort sort=null;
//		if(sortDir.equalsIgnoreCase("asc")) {
//			sort=sort.by(sortBy).ascending();
//			}else {
//				sort=sort.by(sortBy).descending();
//			}
//		
		
		
	//	use tarnary operator for above 
		
		Sort sort=(sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		
		Pageable p = PageRequest.of(pageNumber, pageSize,sort);
		
		Page<Post> pagePost= this.postRepo.findAll(p);
		
		List<Post> allPosts = pagePost.getContent();
		List<PostDto> postDtos= allPosts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

		PostResponse postResponse=new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
	Post post =	this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		
		Category cat= this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "id", categoryId));
		List<Post> posts =this.postRepo.findByCategory(cat);
		
		List<PostDto> postDtos= posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		User user= this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
		List<Post> posts =this.postRepo.findByUser(user);
		
		List<PostDto> postDtos= posts.stream().map((post)-> this.modelMapper.map(posts, PostDto.class)).collect(Collectors.toList());
		return postDtos;

		
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		
		List<Post> posts= this.postRepo.searchByTitle("%"+keyword+"%");
		List<PostDto> postDtos= posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
		
	}

}
