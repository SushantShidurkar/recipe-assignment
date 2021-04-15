package com.recipe.backend.dto;

import java.time.LocalDateTime;


public class RecipeDTO {
	
	private Long id;
    private String name;
    private LocalDateTime created;
    private boolean category;
    private int servings;
    private String ingredients;
    private String instructions;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDateTime getCreated() {
		return created;
	}
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	public boolean isCategory() {
		return category;
	}
	public void setCategory(boolean category) {
		this.category = category;
	}
	public int getServings() {
		return servings;
	}
	public void setServings(int servings) {
		this.servings = servings;
	}
	public String getIngredients() {
		return ingredients;
	}
	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}
	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
    
}
