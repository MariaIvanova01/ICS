import {HttpClient, HttpErrorResponse, HttpParams} from '@angular/common/http';
import { Injectable } from '@angular/core';
import {catchError, EMPTY, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class HttpService {
  private apiUrl: string = 'http://localhost:8080/';
  constructor(private http: HttpClient) {

  }
  ///getImage
  getAllImages(url:string){
    return this.http.get(this.apiUrl + url)
      .pipe(catchError((response: HttpErrorResponse)=>{
        return EMPTY; //eventually error in popup
      }));

  }
  getImageById<T>(url:string, params: { }): Observable<T> {
    return this.http.get<T>(this.apiUrl + url + params)
      .pipe(catchError((response: HttpErrorResponse)=>{
        return EMPTY; //eventually error in popup
      }));
  }

  getImageByTag<T>(params: string[]): Observable<T>{
    let queryParams = new HttpParams();
    for (let i = 0; i < params.length; i++) {
      queryParams = queryParams.append("tags", params[i]);
    }

    return this.http.get<T>(this.apiUrl + 'getImagesByTags', {params: queryParams})
      .pipe(catchError((response: HttpErrorResponse)=>{
        return EMPTY; //eventually error in popup
      }));
  }
  post<T>(url: string, data: any): Observable<T>{
    return this.http.post<T>(this.apiUrl + url, data)
    .pipe(catchError((response: HttpErrorResponse)=>{
      return EMPTY; //eventually error in popup
    }));
  }
}
