package com.recipe.backend;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.recipe.backend.dao.RecipeRepository;
import com.recipe.backend.dto.RecipeDTO;
import com.recipe.backend.entity.Recipe;
import com.recipe.backend.service.RecipeService;

@SpringBootTest
@AutoConfigureMockMvc
class SpringBootRecipeApplicationTests {
	@Autowired
	private MockMvc mockMvc;

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
	
	ObjectMapper getObjectMapper() {
		Jackson2ObjectMapperBuilder builder=new Jackson2ObjectMapperBuilder();
		ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
        return objectMapper;
	}
	@Test
	void contextLoads() {
	}
	@Test
	@DisplayName("Test get recipe- Success")
	void givenRecipe_whenGetRecipe_thenStatus200() throws Exception {
		mockMvc.perform(get("/recipes/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id", is(1)));

	}

	@Test
	@DisplayName("Test get recipes- Success")
	void givenRecipe_whenGetRecipes_thenReturnJsonArray() throws Exception {
		Recipe recipe1 = recipe;
		recipeService.createRecipe(new ModelMapper().map(recipe1, RecipeDTO.class));
		mockMvc.perform(get("/recipes").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(3))).andExpect(jsonPath("$[1].name", is(recipe.getName())));
	}

	@Test
	@DisplayName("Test get recipe- failure")
	void givenRecipe_whenGetRecipe_thenStatus400() throws Exception {
		mockMvc.perform(get("/recipes/5").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
		.andExpect(result -> assertTrue(result.getResolvedException() instanceof NoSuchElementException))
		.andExpect(result -> assertEquals("No value present", result.getResolvedException().getMessage()));

	}
	@Test
	@DisplayName("Test get recipe- failure2")
	void givenRecipe_whenGetRecipe2_thenStatus400() throws Exception {
		mockMvc.perform(get("/recipes/null").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
		.andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentTypeMismatchException));

	}
	@Test
	@DisplayName("Test create Recipe")
	void testCreateRecipe() throws Exception {
		Recipe recipe1 = recipe;
        String json = getObjectMapper().writeValueAsString(recipe1);
		mockMvc.perform(post("/recipes")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Test create Recipe Failure")
	void testCreateRecipeCheckFailure() throws Exception {
		Recipe recipe1 = recipe;
		recipe1.setId(1L);
        String json = getObjectMapper().writeValueAsString(recipe1);
		mockMvc.perform(post("/recipes")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	@DisplayName("Test check constraint")
	void testCheckConstraint() throws Exception {
		Recipe recipe1 = recipe;
		recipe1.setServings(16);
        String json = getObjectMapper().writeValueAsString(recipe1);
		mockMvc.perform(post("/recipes")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest());
	}
	@Test
	@DisplayName("Test update Recipe")
	void testUpdateRecipe() throws Exception {
		Recipe recipe1 = recipe;
		recipe1.setId(1L);
		recipe1.setServings(4);
        String json = getObjectMapper().writeValueAsString(recipe1);
		mockMvc.perform(put("/recipes")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.servings", is(4)));
	}
	
	@Test
	@DisplayName("Test update Recipe Failure")
	void testUpdateRecipeFailure() throws Exception {
		Recipe recipe1 = recipe;
		recipe1.setId(null);
		recipe1.setServings(4);
        String json = getObjectMapper().writeValueAsString(recipe1);
		mockMvc.perform(put("/recipes")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.servings", is(0)));
	}
	
	@Test
	@DisplayName("Test delete recipe- Success") 
	void givenRecipe_whenDeleteRecipe_thenStatus200() throws Exception { 
		
		  Recipe recipe1 = recipe;
		  Recipe recipe2=repository.save(recipe1);
		mockMvc.perform(MockMvcRequestBuilders
				.delete("/recipes/{id}",recipe2.getId())
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Test delete recipe- failure")
	void givenRecipe_whenDeleteRecipe_thenStatus400() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/recipes/5").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest())
		.andExpect(
				result -> assertTrue(result.getResolvedException() instanceof EmptyResultDataAccessException))
		.andExpect(result -> assertEquals("No class com.recipe.backend.entity.Recipe entity with id 5 exists!",
				result.getResolvedException().getMessage()));

	}
	
}
