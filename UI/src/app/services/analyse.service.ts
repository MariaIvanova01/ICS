import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpService} from "./http-services.service";

@Injectable({
  providedIn: 'root'
})
export class AnalyseService {

  constructor(private httpService: HttpService) { }

  processImage(url: string | null | undefined, imageWidth: number, imageHeight: number): Observable<any>{
    console.log(url)
    return this.httpService.post('processImage', {imageURL: url, imageWidth: imageWidth,imageHeight:imageHeight})
  }

  getImageDimensions(url: string): Observable<{ width: number, height: number }> {
    return new Observable((observer) => {
      const img = new Image();
      img.onload = () => {
        observer.next({ width: img.width, height: img.height });
        observer.complete();
      };
      img.onerror = (error) => {
        observer.error(error);
        observer.complete();
      };
      img.src = url;
    });
  }
}
