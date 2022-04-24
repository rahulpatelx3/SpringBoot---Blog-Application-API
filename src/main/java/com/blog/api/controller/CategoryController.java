package com.blog.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blog.api.payload.APIResponse;
import com.blog.api.payload.CategoryDto;
import com.blog.api.service.Impl.CategoryServiceImpl;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	private CategoryServiceImpl categoryService;
	
	//Post - create category
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
		CategoryDto createCategory=this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.CREATED);
	}
	
	//put - update category
	@PutMapping("/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable("id") Integer categoryId){
		CategoryDto updateCategory=this.categoryService.updateCategory(categoryDto,categoryId);
		return new ResponseEntity<CategoryDto>(updateCategory,HttpStatus.OK);
	}
	
	//Delete - delete category
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<APIResponse> deleteCategory(@PathVariable int categoryId){
		this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity<APIResponse>(new APIResponse("category deleted success",true),HttpStatus.OK);	
	}
	
	//Get - get category by id
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable int categoryId){
		CategoryDto categoryDto=this.categoryService.getCategoryById(categoryId);
		return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);	
	}
	
	//Get - get all category
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		List<CategoryDto> categoryDto=this.categoryService.getAllCategory();
		return new ResponseEntity<List<CategoryDto>>(categoryDto,HttpStatus.OK);	
	}
	
}
