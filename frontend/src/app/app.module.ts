import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule} from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomeComponent } from './components/home/home.component';
import { MatToolbarModule } from  '@angular/material/toolbar';
import {MatCardModule} from '@angular/material/card';
import {MatButtonModule} from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import {MatFormFieldModule} from "@angular/material/form-field";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatDialogModule, MAT_DIALOG_DATA} from "@angular/material/dialog";
import { CreateRecipeDialogComponent } from './components/create-recipe-dialog/create-recipe-dialog.component';
import { MatInputModule} from '@angular/material/input';
import {MatRadioModule} from '@angular/material/radio';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { ShowRecipeDialogComponent } from './components/show-recipe-dialog/show-recipe-dialog.component';
@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    CreateRecipeDialogComponent,
    ShowRecipeDialogComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    MatDialogModule,
    MatFormFieldModule,
    FormsModule,
    MatInputModule,
    MatRadioModule,
    MatSnackBarModule,
    ReactiveFormsModule.withConfig({warnOnNgModelWithFormControl: 'never'})
    
  ],
  providers: [{ provide: MAT_DIALOG_DATA, useValue: 'dialogData'}],
  bootstrap: [AppComponent]
})
export class AppModule { }
