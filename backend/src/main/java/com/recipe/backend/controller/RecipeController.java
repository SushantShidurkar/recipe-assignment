package com.recipe.backend.controller;


import java.util.List;
import java.util.NoSuchElementException;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.recipe.backend.dto.RecipeDTO;
import com.recipe.backend.service.RecipeService;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RecipeController {

	Logger logger= LoggerFactory.getLogger(RecipeController.class);
	
	@Autowired
	RecipeService recipeService;

	
	@GetMapping("/recipes")
	public ResponseEntity<List<RecipeDTO>> getAllRecipes(){
		logger.info("getAllRecipe called.");
		return new ResponseEntity<>(recipeService.getAllRecipes(),HttpStatus.OK);
	}
	
	@GetMapping("/recipes/{recipeId}")
	public ResponseEntity<RecipeDTO> getRecipe(@PathVariable("recipeId") Long id){
		logger.info("getRecipe called.");
		if(id!=null) {
			RecipeDTO recipeDto=recipeService.getRecipe(id);
			return new ResponseEntity<>(recipeDto,HttpStatus.OK);
		}else {
			logger.error("Error in getRecipe");
			throw new NoSuchElementException();
		}
	}
	
	@DeleteMapping("/recipes/{recipeId}")  
	public ResponseEntity<String> deleteRecipe(@PathVariable("recipeId") Long id){
		logger.info("deleteRecipe called.");
		if(id!=null) {
			recipeService.deleteRecipe(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			logger.error("Error in delete recipe");
			return new ResponseEntity<>("Please provide valid input",HttpStatus.BAD_REQUEST);
		}

	}
	
	@PostMapping("/recipes")  
	public ResponseEntity<String> createRecipe(@Valid @RequestBody RecipeDTO recipeDTO)  
	{  
		logger.info("createRecipe called.");
		if(null==recipeDTO.getId()) {
			recipeService.createRecipe(recipeDTO);  
			return new ResponseEntity<>(HttpStatus.OK); 
		}else {
			logger.error("Id found in create recipe.");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
		}
	}
	
	@PutMapping("/recipes")  
	public ResponseEntity<RecipeDTO> updateRecipe(@Valid @RequestBody RecipeDTO recipeDTO)   
	{  
		logger.info("updateRecipe called.");
		RecipeDTO recipeDto=new RecipeDTO();
		if(null!=recipeDTO.getId()) {
			recipeDto=recipeService.updateRecipe(recipeDTO);
			return new ResponseEntity<>(recipeDto,HttpStatus.OK);
		}else {
			logger.error("Id not found in update recipe.");
			return new ResponseEntity<>(recipeDto,HttpStatus.BAD_REQUEST);
		}
	} 

}
