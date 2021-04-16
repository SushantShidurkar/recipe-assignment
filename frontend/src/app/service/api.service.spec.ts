import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { ApiService } from './api.service';
import { RecipeDTO } from '../model/RecipeDTO';
import { environment } from 'src/environments/environment';
import { Recipe } from '../model/recipe.model';

TestBed.configureTestingModule({
  imports: [HttpClientTestingModule],
  providers: []
});

describe('ApiService', () => {
  let service: ApiService;
  let httpMock: HttpTestingController;
  let apiService: ApiService;
  let httpClient: HttpClient;
  let recipeDTO: RecipeDTO;
  let recipe:Recipe;
  beforeEach(() => {
    
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ApiService]
    });
    service = TestBed.inject(ApiService);
    httpClient = TestBed.inject(HttpClient);
    apiService = TestBed.inject(ApiService);
    httpMock = TestBed.inject(HttpTestingController);
    recipeDTO = new RecipeDTO();
    recipe = new Recipe();
    recipeDTO.id = 1;
    recipeDTO.created = new Date();
    recipeDTO.name = "Dish";
    recipeDTO.servings = 2;
    recipeDTO.category = true;
    recipeDTO.ingredients = ["Oil"];
    recipeDTO.instructions = "Just pour oil on pan";

    recipe.name = "Dish";
    recipe.servings = 2;
    recipe.category = true;
    recipe.ingredients = "Oil";
    recipe.instructions = "Just pour oil on pan";
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
  afterEach(() => {
    httpMock.verify(); //Verifies that no requests are outstanding.
  });
  
  it('get recipe should give one recipe', () => {
    
    apiService.getRecipe(1).subscribe((res) => {
      expect(res).toEqual(recipeDTO);

    });
    const req = httpMock.expectOne(req => req.method === 'GET' && req.url === environment.baseURL + 'recipes/1');
    expect(req.request.method).toEqual("GET");
    req.flush(recipeDTO);
    httpMock.verify();
    // const req = httpMock.expectOne({ method: 'GET', url: environment.baseURL + "recipes/1" });
    //const req = httpMock.match({ method: 'GET', url: environment.baseURL + "recipes/1" });
  });

  it('get recipe should give all recipes', () => {
    var recipes: RecipeDTO[] = new Array;
    recipes.push(recipeDTO);
    apiService.getRecipes().subscribe((res) => {
      expect(res).toEqual(recipes);

    });

    const req = httpMock.expectOne({ method: 'GET', url: environment.baseURL + "recipes" });
    expect(req.request.method).toEqual("GET");
    req.flush(recipes);
    httpMock.verify();

  });

  it('delete recipe should delete one recipe',()=>{
    apiService.deleteRecipe(1).subscribe((res) => {
      expect(res).toEqual("");

    });
    const req = httpMock.expectOne(req=>req.method==='DELETE' && req.url===environment.baseURL + 'recipes/1');
    expect(req.request.method).toEqual("DELETE");
    //req.flush();
    httpMock.verify();
  });
  it('Add recipe should add one recipe',()=>{
    apiService.saveRecipe(recipe).subscribe((res) => {
      expect(res).toEqual("");

    });
    const req = httpMock.expectOne(req=>req.method==='POST' && req.url===environment.baseURL + 'recipes');
    expect(req.request.method).toEqual("POST");
    //req.flush();
    httpMock.verify();
  });
  
  it('Update recipe should add update recipe',()=>{
    apiService.updateRecipe(recipe).subscribe((res) => {
      expect(res).toEqual("");

    });
    const req = httpMock.expectOne(req=>req.method==='PUT' && req.url===environment.baseURL + 'recipes');
    expect(req.request.method).toEqual("PUT");
    //req.flush();
    httpMock.verify();
  });
});
