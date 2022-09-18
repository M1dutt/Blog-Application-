package com.mahesh.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mahesh.blog.payloads.ApiResponse;
import com.mahesh.blog.payloads.CategoryDto;
import com.mahesh.blog.payloads.PostDto;
import com.mahesh.blog.payloads.UserDto;
import com.mahesh.blog.services.CategoryService;
import com.mahesh.blog.services.UserService;

@RestController
@RequestMapping("/api/categories")
public class CategoryControllers {
	
	
	@Autowired
	private CategoryService categoryService;
	
	//  for creating user
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		
		CategoryDto createdCategory = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<>(createdCategory,HttpStatus.CREATED);
	}

	
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updatecategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer catId  ){
		
		CategoryDto updatedCategory = this.categoryService.updateCatogory(categoryDto, catId);
		return ResponseEntity.ok(updatedCategory);
		
	}
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") Integer cid){
		
		this.categoryService.deleteCategory(cid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully",true), HttpStatus.OK);
		
		
	}
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getCategories(){
		
	return ResponseEntity.ok(this.categoryService.getCategories());
		
	}
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer categoryId){
		
		
	return ResponseEntity.ok(this.categoryService.getCategory(categoryId));
		
	}
	
	

}
