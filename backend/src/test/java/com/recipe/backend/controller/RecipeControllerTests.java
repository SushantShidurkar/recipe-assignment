package com.recipe.backend.controller;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.recipe.backend.entity.Recipe;

@SpringBootTest
@AutoConfigureMockMvc
class RecipeControllerTests {
	Recipe recipe;
	
	@Autowired
	RecipeController recipeController;
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
	@DisplayName("Test delete recipe failure")
	void deleteRecipe() {
		assertEquals("Please provide valid input", recipeController.deleteRecipe(null).getBody());
	}
	


}
