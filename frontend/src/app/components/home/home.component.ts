import { Component, OnInit } from '@angular/core';
import { Recipe } from 'src/app/model/recipe.model';
import { RecipeDTO } from 'src/app/model/RecipeDTO';
import { ApiService } from 'src/app/service/api.service';
import { MatDialog, MatDialogConfig } from "@angular/material/dialog";
import { CreateRecipeDialogComponent } from '../create-recipe-dialog/create-recipe-dialog.component';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ShowRecipeDialogComponent } from '../show-recipe-dialog/show-recipe-dialog.component';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  recipes: Recipe[] = [];

  constructor(private apiService: ApiService, private dialog: MatDialog, private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.getRecipesList();
  }

  openSnackBar(message: string) {
    this._snackBar.open(message, "Close", {
      duration: 5000,
    });

  }
  getRecipesList(): void {
    this.apiService.getRecipes().subscribe(
      (response) => {
        
        this.recipes = response;
      },
      (error) => {
        console.error('Request failed with error');
        
      });
  }
  addRecipe(): void {
    this.openDialog(1, null);
  }

  saveOrUpdateRecipe(recipeDTO: RecipeDTO) {

    if (recipeDTO.id > 0) {
      
      this.apiService.updateRecipe(this.dtoToModel(recipeDTO)).subscribe(
        (response) => {
          this.openSnackBar("Recipe updated successfully!");
          this.getRecipesList();
        },
        (error) => {
          let httpError: HttpErrorResponse;
          httpError = error;
          if (httpError.status == 400) {
            let errorsArray = httpError.error;
            let message: string = "";
            let i = 0;
            errorsArray.forEach(element => {
              message += element;
              message += ". ";
              i++;
              if (i == errorsArray.length) {
                this.openSnackBar(message);
              }
            });

          } else {
            this.openSnackBar("Error in updating. Please try again");
          }

        }
      );

    } else {
      this.apiService.saveRecipe(this.dtoToModel(recipeDTO)).subscribe(
        (response) => {
          this.openSnackBar("Recipe added successfully!");
          this.getRecipesList();
        },
        (error) => {
          let httpError: HttpErrorResponse;
          httpError = error;
          if (httpError.status == 400) {
            let errorsArray = httpError.error;
            let message: string = "";
            let i = 0;
            errorsArray.forEach(element => {
              message += element;
              message += ". ";
              i++;
              if (i == errorsArray.length) {
                this.openSnackBar(message);
              }
            });

          } else {
            this.openSnackBar("Error in saving. Please try again");
          }
        }
      );
    }
  }
  updateRecipe(id) {
    this.apiService.getRecipe(id).subscribe((response) => {
      let recipe: Recipe = response;
      this.openDialog(2, this.modelToDto(recipe));
    },
      (error) => {
        
        this.openSnackBar("Something went wrong");
      }

    );

  }
  showRecipe(id) {
    this.apiService.getRecipe(id).subscribe((response) => {
      let recipe: Recipe = response;
      this.openDialog(3, this.modelToDto(recipe));
    },
      (error) => {
        this.openSnackBar("Something went wrong");
      }

    );

  }

  deleteRecipe(id): void {

    this.apiService.deleteRecipe(id).subscribe((response) => {
      this.openSnackBar("Recipe deleted successfully!");
      this.getRecipesList();
    },
      (error) => {
        
        this.openSnackBar("Error in deleting. Please try again");
      }

    );
  }
  dtoToModel(recipeDTO: RecipeDTO) {
    let recipe: Recipe = new Recipe();
    recipe.name = recipeDTO.name;
    recipe.category = recipeDTO.category;
    recipe.ingredients = recipeDTO.ingredients.toString();
    recipe.instructions = recipeDTO.instructions;
    recipe.servings = recipeDTO.servings;
    recipe.id = recipeDTO.id;
    recipe.created = recipeDTO.created;
    return recipe;
  }
  modelToDto(recipe: Recipe) {
    let recipeDTO: RecipeDTO = new RecipeDTO();
    recipeDTO.name = recipe.name;
    recipeDTO.category = recipe.category;
    recipeDTO.ingredients = recipe.ingredients.split(',');
    recipeDTO.instructions = recipe.instructions;
    recipeDTO.servings = recipe.servings;
    recipeDTO.id = recipe.id;
    recipeDTO.created = recipe.created;
    return recipeDTO;
  }

  openDialog(id, data) {

    const dialogConfig = new MatDialogConfig();
    dialogConfig.minWidth = "450px";
    dialogConfig.minHeight = "200px";
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.data = data;
    if (id == 3) {
      dialogConfig.disableClose = false;
      const dialogRef = this.dialog.open(ShowRecipeDialogComponent, dialogConfig);
      dialogRef.afterClosed().subscribe(
        data => {
        },
        error => {
          
        }

      );
    } else {
      const dialogRef = this.dialog.open(CreateRecipeDialogComponent, dialogConfig);
      dialogRef.afterClosed().subscribe(
        data => {
          if (data != null) {
            
            this.saveOrUpdateRecipe(data);
          }

        },
        error => {
          
        }

      );
    }

  }

}


