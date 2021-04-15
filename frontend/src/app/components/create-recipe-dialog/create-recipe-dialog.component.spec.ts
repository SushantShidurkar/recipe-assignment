import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { MatDialogModule, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';

import { CreateRecipeDialogComponent } from './create-recipe-dialog.component';

describe('CreateRecipeDialogComponent', () => {
  let component: CreateRecipeDialogComponent;
  let fixture: ComponentFixture<CreateRecipeDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports:[ReactiveFormsModule ,
        MatFormFieldModule,
        MatInputModule,
        MatDialogModule,
        NoopAnimationsModule],
      
    providers: [
        { provide: MatDialogRef, useValue: {} },
        { provide: MAT_DIALOG_DATA, useValue: { myData: null } }
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
});
