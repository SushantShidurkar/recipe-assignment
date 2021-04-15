import { Component, Inject, OnInit, ViewEncapsulation } from '@angular/core';
import { FormBuilder, FormGroup ,Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from "@angular/material/dialog";
import { RecipeDTO } from 'src/app/model/RecipeDTO';
@Component({
    selector: 'app-create-recipe-dialog',
    templateUrl: './create-recipe-dialog.component.html',
    styleUrls: ['./create-recipe-dialog.component.css']
})
export class CreateRecipeDialogComponent implements OnInit {

    recipe:RecipeDTO;
    form: FormGroup;
    operation:string;

    constructor(
        private fb: FormBuilder,
        private dialogRef: MatDialogRef<CreateRecipeDialogComponent>,
        @Inject(MAT_DIALOG_DATA) data) {
            if(data!=null){
                this.recipe=data;
                this.operation="Update";
                
            }else{
                this.recipe = new RecipeDTO();
                this.operation="Add";
            }
    }
    ngOnInit() {
        
        this.form = this.fb.group({
            name: ['', Validators.required],
            category: ['', Validators.required],
            servings: ['', [Validators.required, Validators.min(0),Validators.max(15)]],
            ingredients: [''],
            instructions: ['', Validators.required],
        });
    }

    save() {
        this.dialogRef.close(this.recipe);
    }

    close() {
        this.dialogRef.close();
    }
    addIngredient(ingredient: string): void {
        if (!ingredient) {
            return;
        }
        this.recipe.ingredients.push(ingredient);
    }

    removeIngredient(ingredient: string): void {
        const index: number = this.recipe.ingredients.indexOf(ingredient);
        if (index !== -1) {
            this.recipe.ingredients.splice(index, 1);
        }
    }
}