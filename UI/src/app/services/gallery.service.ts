import { Injectable } from '@angular/core';
import {HttpService} from "./http-services.service";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class GalleryService {

  constructor(private httpService: HttpService) { }
  getAllImages(): Observable<any>{
    return this.httpService.getAllImages('getAllImages');
  }
  getImageByTags(tags: string[]):Observable<any>{
    return this.httpService.getImageByTag(tags);
  }
}
