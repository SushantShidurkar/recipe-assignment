import { HttpClient,HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { NgModule } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { MatDialogModule, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { Observable } from 'rxjs/internal/Observable';
import { RecipeDTO } from 'src/app/model/RecipeDTO';
import { ApiService } from 'src/app/service/api.service';
import { environment } from 'src/environments/environment';

import { HomeComponent } from './home.component';

describe('HomeComponent', () => {
  let component: HomeComponent;
  let fixture: ComponentFixture<HomeComponent>;
  let httpMock: HttpTestingController;
  let apiService: ApiService;
  let httpClient:HttpClient;
  let recipeDTO:RecipeDTO;
  beforeEach(() => {
    HomeComponent.prototype.ngOnInit=()=>{};
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule,MatDialogModule,MatSnackBarModule,NoopAnimationsModule ],
      providers: [{provide:ApiService},
        { provide: MatDialogRef, useValue: {} },
        { provide: MAT_DIALOG_DATA, useValue: { myData: null } }
    ],
      declarations: [ HomeComponent]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    httpClient = TestBed.inject(HttpClient);
    apiService = TestBed.inject(ApiService);
    httpMock = TestBed.inject(HttpTestingController);
    recipeDTO=new RecipeDTO();
    
    recipeDTO.created=new Date();
    recipeDTO.name="Dish";
    recipeDTO.servings=2;
    recipeDTO.category=true;
    recipeDTO.ingredients=["Oil"];
    recipeDTO.instructions="Just pour oil on pan";
    httpMock.verify({ignoreCancelled:true});
  });
  afterEach(() => {
    
    httpMock.verify({ignoreCancelled:true}); //Verifies that no requests are outstanding.
  });
  it('get recipe should give one recipe',()=>{
    component.showRecipe(1);
    const req = httpMock.expectOne(req=>req.method==='GET' && req.url===environment.baseURL + 'recipes/1');
    expect(req.request.method).toEqual("GET");
    req.flush(recipeDTO);
    
  });
  
  it('get recipe should give all recipes',()=>{
    recipeDTO.id=1;
    var recipes:RecipeDTO[]=new Array;
    recipes.push(recipeDTO);
    component.getRecipesList();
    const req = httpMock.expectOne({ method: 'GET', url: environment.baseURL + "recipes" });
    expect(req.request.method).toEqual("GET");
    req.flush(recipes);
    
  
  });
  // it('Save recipe should save one recipe',()=>{
    
  //   delete recipeDTO.id;
    
  //   component.saveOrUpdateRecipe(recipeDTO);
  //   expect(1).toEqual(1);
  //   //spyOn(component,"getRecipesList").and.callFake(function(){return null;});
  //   const req = httpMock.expectOne(req=>req.method==='POST' && req.url===environment.baseURL + 'recipes/');
  //   expect(req.request.method).toEqual("POST");
  //   req.flush(recipeDTO);
  // });
  
  it('delete recipe should delete one recipe',()=>{
    spyOn(component,"getRecipesList").and.callFake(function(){return null;});
    component.deleteRecipe(1);
    //const reqs=httpMock.match(environment.baseURL + 'recipes');
    const req = httpMock.expectOne(req=>req.method==='DELETE' && req.url===environment.baseURL + 'recipes/1');
    
    req.flush({});
  });
  it('should create', () => {
    component.openDialog(3,null);
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});


