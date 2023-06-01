import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpService} from "./http-services.service";

@Injectable({
  providedIn: 'root'
})
export class AnalyseService {

  constructor(private httpService: HttpService) { }
  processImage(url: string,imageWidth: number, imageHeight:number): Observable<any>{
    console.log(url)
    return this.httpService.post('processImage', {imageURL: url, imageWidth: imageWidth,imageHeight:imageHeight})
  }
}
