import { Injectable } from '@angular/core';
import {HttpService} from "./http-services.service";
import {Observable} from "rxjs"

@Injectable({
  providedIn: 'root'
})
export class SingleImageService {

  constructor(private httpService: HttpService) { }
  getImageById(): Observable<any>{
    return this.httpService.getImageById('getImage', 'imageUrl')
  }
}
