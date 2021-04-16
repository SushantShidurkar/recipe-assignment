import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { NgModule } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { MatDialogModule, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { doesNotReject } from 'node:assert';
import { RecipeDTO } from 'src/app/model/RecipeDTO';
import { ApiService } from 'src/app/service/api.service';
import { environment } from 'src/environments/environment';

import { HomeComponent } from './home.component';

describe('HomeComponent', () => {
  let component: HomeComponent;
  let fixture: ComponentFixture<HomeComponent>;
  let httpMock: HttpTestingController;
  let apiService: ApiService;
  let recipeDTO:RecipeDTO;
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule,MatDialogModule,MatSnackBarModule ],
      providers: [{provide:ApiService},
        { provide: MatDialogRef, useValue: {} },
        { provide: MAT_DIALOG_DATA, useValue: { myData: null } }
    ],
      declarations: [ HomeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    apiService = TestBed.inject(ApiService);
    httpMock = TestBed.inject(HttpTestingController);
    recipeDTO=new RecipeDTO();
  });

  it('get recipe should give one recipe',()=>{
    recipeDTO.id=1;
    recipeDTO.created=new Date();
    recipeDTO.name="Dish";
    recipeDTO.servings=2;
    recipeDTO.category=true;
    recipeDTO.ingredients=["Oil"];
    recipeDTO.instructions="Just pour oil on pan";
    apiService.getRecipe(1).subscribe((res)=>{
      expect(res).toEqual(recipeDTO);
      
    });

    // const req = httpMock.expectOne({ method: 'GET', url: environment.baseURL + "recipes/1" });
    // expect(req.request.method).toEqual("GET");
    // req[0].flush(recipeDTO);
    // httpMock.verify();
  
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

@NgModule({
  imports: [MatDialogModule],
  entryComponents: [
    
  ],
})
class HomeComponentTestModule { }
