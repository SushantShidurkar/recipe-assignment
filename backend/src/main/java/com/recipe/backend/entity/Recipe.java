package com.recipe.backend.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

@Entity  
@Table
public class Recipe {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Long id;
	
	@NotBlank(message="Name must not be empty")
    @Pattern(regexp="^$|[a-zA-Z0-9 ]+$", message="Name must not include special characters.")
	@Column
    private String name;
	
	@Past
	@Column
    private LocalDateTime created;
	
	@NotNull
	@Column
    private boolean category;
	
    @Min(value=1,message="Servings must be greater than or equal to 1")
    @Max(value=15,message="Servings must be less than or equal to 15")
	@Column
    private int servings;
	
	@NotBlank(message="Ingredients must not be empty")
	@Pattern(regexp="^$|[a-zA-Z0-9, ]+$", message="Ingredients must not include special characters.")
	@Column
    private String ingredients;
	
	@NotBlank(message="Instructions must not be empty")
	@Pattern(regexp="^$|[a-zA-Z0-9,. \r\n]+$", message="Instructions must not include special characters.")
    @Column(length = 5000)
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
