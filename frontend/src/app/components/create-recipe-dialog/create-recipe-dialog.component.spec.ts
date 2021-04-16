import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { MatDialog, MatDialogModule, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { RecipeDTO } from 'src/app/model/RecipeDTO';

import { CreateRecipeDialogComponent } from './create-recipe-dialog.component';

describe('CreateRecipeDialogComponent', () => {
  let component: CreateRecipeDialogComponent;
  let fixture: ComponentFixture<CreateRecipeDialogComponent>;
  let dialogSpy: jasmine.Spy;
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports:[ReactiveFormsModule ,
        MatFormFieldModule,
        MatInputModule,
        MatDialogModule,
        NoopAnimationsModule],
      
    providers: [
        { provide: MatDialogRef, useValue: {} },
        { provide: MAT_DIALOG_DATA, useValue: { myData: null }}
    ],
      declarations: [ CreateRecipeDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateRecipeDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
  
  it('should create', () => {
    expect(component).toBeTruthy();
  });
  it('form should be invalid', () => {
    component.form.controls["name"].setValue("");
    component.form.controls["instructions"].setValue("");
    expect(component.form.valid).toBeFalsy();
  });
  it('form should be valid', () => {
    component.form.controls["name"].setValue("Dish");
    component.form.controls["instructions"].setValue("Do this");
    component.form.controls["servings"].setValue("2");
    component.form.controls["category"].setValue(true);
    expect(component.form.valid).toBeTruthy();
  });
  
  it('Check if ingredient is added', () => {
    component.recipe=new RecipeDTO();
    component.addIngredient("Oil");
    expect(component.recipe.ingredients.length).toBe(1);
  });
  it('Check if ingredient is removed', () => {
    component.recipe=new RecipeDTO();
    component.addIngredient("Oil");
    component.removeIngredient("Oil");
    expect(component.recipe.ingredients.length).toBe(0);
  });
  
  
});
