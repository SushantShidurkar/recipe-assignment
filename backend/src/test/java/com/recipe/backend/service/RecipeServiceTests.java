package com.recipe.backend.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.recipe.backend.dao.RecipeRepository;
import com.recipe.backend.dto.RecipeDTO;
import com.recipe.backend.entity.Recipe;

@SpringBootTest
@AutoConfigureMockMvc
class RecipeServiceTests {
	@Autowired
	private RecipeService recipeService;

	@Autowired
	private RecipeRepository repository;
	Recipe recipe;

	@BeforeEach
	void setup() {
		recipe = new Recipe();
		LocalDateTime lt = LocalDateTime.now();
		recipe.setName("Boiling Water");
		recipe.setServings(2);
		recipe.setCreated(lt);
		recipe.setCategory(true);
		recipe.setIngredients("Water");
		recipe.setInstructions("Start gas stove and boil the water.");
	}
	
	@Test
	@DisplayName("Test Save service")
	void testSaveRecipe() {
		Recipe recipe1 = recipe;
		recipeService.createRecipe(new ModelMapper().map(recipe1, RecipeDTO.class));
		Optional<Recipe> recipe2 = repository.findById(2L);
		Assertions.assertSame(recipe2.get().getName(), recipe1.getName());
	}
	@Test
	@DisplayName("Test Update Service")
	void testUpdateRecipe() {
		Recipe recipe1 = recipe;
		recipe1.setId(1L);
		recipe1.setServings(4);
		recipeService.updateRecipe(new ModelMapper().map(recipe1, RecipeDTO.class));
		Optional<Recipe> recipe2 = repository.findById(1L);
		Assertions.assertSame(recipe2.get().getServings(), recipe1.getServings());
	}
	@Test
	@DisplayName("Test get Service")
	void testgetRecipe() {
		Assertions.assertSame(1L, recipeService.getRecipe(1L).getId());
	}
	@Test
	@DisplayName("Test get recipes Service")
	void testgetRecipes() {
		Assertions.assertTrue(recipeService.getAllRecipes().size()>0);
	}
}
