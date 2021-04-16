package com.recipe.backend.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.recipe.backend.dao.RecipeRepository;
import com.recipe.backend.dto.RecipeDTO;
import com.recipe.backend.entity.Recipe;

@Service
public class RecipeService {
	@Autowired
	RecipeRepository recipeRepository;
	Logger logger= LoggerFactory.getLogger(RecipeService.class);

	public List<RecipeDTO> getAllRecipes() {

		logger.info("Service getAllRecipe called.");
		List<RecipeDTO> recipeList= new ArrayList<>();
		ModelMapper modelMapper = new ModelMapper();
		recipeRepository.findAll().forEach(recipe -> recipeList.add(modelMapper.map(recipe,RecipeDTO.class)));

		return recipeList;
	}


	public RecipeDTO getRecipe(Long id) {
		logger.info("Service getRecipe called.");
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(recipeRepository.findById(id).get(), RecipeDTO.class);

	}


	public void deleteRecipe(Long id) {
		logger.info("Service deleteRecipe called.");
		recipeRepository.deleteById(id);

	}


	public void createRecipe(RecipeDTO recipeDto) {
		logger.info("Service createRecipe called.");
		LocalDateTime lt= LocalDateTime.now();
		recipeDto.setCreated(lt);
		ModelMapper modelMapper = new ModelMapper();
		Recipe recipe=modelMapper.map(recipeDto, Recipe.class);
		recipeRepository.save(recipe);

	}

	public RecipeDTO updateRecipe(RecipeDTO recipeDto) {
		logger.info("Service updateRecipe called.");
		LocalDateTime lt= LocalDateTime.now();
		recipeDto.setCreated(lt);
		ModelMapper modelMapper = new ModelMapper();
		Recipe recipe=modelMapper.map(recipeDto, Recipe.class);
		return modelMapper.map(recipeRepository.save(recipe), RecipeDTO.class);
	}

}
