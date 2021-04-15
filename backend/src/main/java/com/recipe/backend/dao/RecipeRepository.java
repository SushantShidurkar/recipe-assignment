package com.recipe.backend.dao;

import org.springframework.data.repository.CrudRepository;

import com.recipe.backend.entity.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long>{

}
