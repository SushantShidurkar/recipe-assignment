package com.recipe.backend.util;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.recipe.backend.dao.RecipeRepository;
import com.recipe.backend.entity.Recipe;

@Component
public class DataLoader implements ApplicationRunner{
	private RecipeRepository recipeRepository;

    @Autowired
    public DataLoader(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }
	@Override
	public void run(ApplicationArguments args) throws Exception {
		Recipe recipe1= new Recipe();
		LocalDateTime lt= LocalDateTime.now();
		recipe1.setName("Omelette Recipe");
		recipe1.setServings(2);
		recipe1.setCreated(lt);
		recipe1.setCategory(false);
		recipe1.setIngredients("3 eggs ,Cooking oil, Salt");
		recipe1.setInstructions("STEP 1 \n"+
		"Season the beaten eggs well with salt and pepper. Heat the oil in a non stick frying pan over "+
		"a medium low heat.\n STEP 2 \n Pour the eggs into the pan, tilt the pan ever so slightly from one "+
		"side to another to allow the eggs to swirl and cover the surface of the pan completely. Let the mixture"+
		"cook for about 20 seconds then scrape a line through the middle with a spatula. Serve it.Enjoy.");
		recipeRepository.save(recipe1);
		
		
	}

}
