package com.mahesh.blog.services;

import java.util.List;

import com.mahesh.blog.payloads.CategoryDto;

public interface CategoryService {
	
	// create 
	
	CategoryDto createCategory(CategoryDto categoryDto);
	
	
	// update 
	
	
	//delete 
	void deleteCategory(Integer categoryId);
	
	// get single
	CategoryDto getCategory(Integer categoryId);
	
	// get all 
	List<CategoryDto> getCategories();


	CategoryDto updateCatogory(CategoryDto categoryDto, Integer categoryId);


	
}
