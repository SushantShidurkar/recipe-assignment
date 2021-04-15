import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from "@angular/material/dialog";
import { RecipeDTO } from 'src/app/model/RecipeDTO';
@Component({
  selector: 'app-show-recipe-dialog',
  templateUrl: './show-recipe-dialog.component.html',
  styleUrls: ['./show-recipe-dialog.component.css']
})
export class ShowRecipeDialogComponent implements OnInit {
  recipe:RecipeDTO;
  constructor(
    private dialogRef: MatDialogRef<ShowRecipeDialogComponent>,
        @Inject(MAT_DIALOG_DATA) data) {
            if(data!=null){
                this.recipe=data;
            }else{
                this.recipe = new RecipeDTO();
                this.recipe.name="No Data Found. Please retry.";
            }
  }

  ngOnInit(): void {
  }

}
