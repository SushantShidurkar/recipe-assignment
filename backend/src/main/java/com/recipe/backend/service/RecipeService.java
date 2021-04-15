package com.recipe.backend.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recipe.backend.dao.RecipeRepository;
import com.recipe.backend.dto.RecipeDTO;
import com.recipe.backend.entity.Recipe;

@Service
public class RecipeService {
	@Autowired
	RecipeRepository recipeRepository;


	public List<RecipeDTO> getAllRecipes() {

		List<RecipeDTO> recipeList= new ArrayList<>();
		ModelMapper modelMapper = new ModelMapper();
		recipeRepository.findAll().forEach(recipe -> recipeList.add(modelMapper.map(recipe,RecipeDTO.class)));

		return recipeList;
	}


	public RecipeDTO getRecipe(Long id) {

		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(recipeRepository.findById(id).get(), RecipeDTO.class);

	}


	public void deleteRecipe(Long id) {

		recipeRepository.deleteById(id);

	}


	public void createRecipe(RecipeDTO recipeDto) {
		LocalDateTime lt= LocalDateTime.now();
		recipeDto.setCreated(lt);
		ModelMapper modelMapper = new ModelMapper();
		Recipe recipe=modelMapper.map(recipeDto, Recipe.class);
		recipeRepository.save(recipe);
	}

	public RecipeDTO updateRecipe(RecipeDTO recipeDto) {
		LocalDateTime lt= LocalDateTime.now();
		recipeDto.setCreated(lt);
		ModelMapper modelMapper = new ModelMapper();
		Recipe recipe=modelMapper.map(recipeDto, Recipe.class);
		return modelMapper.map(recipeRepository.save(recipe), RecipeDTO.class);
	}

}
