import { Injectable } from '@angular/core';
import {Recipe} from "../model/recipe.model";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from '../../environments/environment';
@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private baseURL=environment.baseURL;
  
  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json','Access-Control-Allow-Origin':'*'})
  }

  constructor(private http: HttpClient) {
  }

  getRecipes(): Observable<any> {
    return this.http.get(this.baseURL+"recipes",this.httpOptions);
  }
  saveRecipe(recipe:Recipe): Observable<any> {
    return this.http.post(this.baseURL+"recipes",recipe,this.httpOptions);
  }
  deleteRecipe(id:number): Observable<any> {
    return this.http.delete(this.baseURL+"recipes/"+id,this.httpOptions);
  }
  getRecipe(id:number): Observable<any> {
    return this.http.get(this.baseURL+"recipes/"+id,this.httpOptions);
  }
  updateRecipe(recipe:Recipe): Observable<any> {
    return this.http.put(this.baseURL+"recipes",recipe,this.httpOptions);
  }
}
