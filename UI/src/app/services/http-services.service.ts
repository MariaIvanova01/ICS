import {HttpClient, HttpErrorResponse} from '@angular/common/http';
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

 /* getImageByTag<T>(url: string, params: { }): Observable<T>{
    console.log(params);
    return this.http.get<T>(this.apiUrl + url + params)
      .pipe(catchError((response: HttpErrorResponse)=>{
        return EMPTY; //eventually error in popup
      }));
  }*/
  post<T>(url: string, data: any): Observable<T>{
    return this.http.post<T>(this.apiUrl + url, data)
    .pipe(catchError((response: HttpErrorResponse)=>{
      return EMPTY; //eventually error in popup
    }));
  }
}
