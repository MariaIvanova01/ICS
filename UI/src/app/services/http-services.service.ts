import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import { Injectable } from '@angular/core';
import {catchError, EMPTY, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class HttpService {
  private apiUrl: string = 'http://localhost:8080/';
  constructor(private http: HttpClient) {

  }
  get<T>(url:string, params: { }): Observable<T> {

    return this.http.get<T>(this.apiUrl + url + this.setQueryParams(params))
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

  setQueryParams(params: {}){
    if(params != ''){

    }
    return params;
}
}
