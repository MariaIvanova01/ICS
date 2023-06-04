import {Component} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AnalyseService} from "../services/analyse.service";
import { Router } from '@angular/router';
import { tap } from 'rxjs/operators';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.scss']
})
export class HomepageComponent{
  image: Image = new Image();
  imageForm = new FormGroup({
    imageUrl: new FormControl('', Validators.required),
  });
  constructor(private analyseService: AnalyseService,
              private router: Router) {
  }

  submit(){
    const imageUrl = this.imageForm.get('imageUrl')?.value;
    console.log(imageUrl)
    this.analyseService.processImage(imageUrl, 200,200)
      .pipe(
      tap(response =>{
        console.log(response);
        this.image.imageURL = imageUrl;
        this.image.tags = response.tags;
      })
      )
      .subscribe(()=> {
        this.openImage();
    })
  }
  openImage(){
    this.router.navigateByUrl('/single-image-view',{ state: this.image});
  }
}
class Image{
  imageURL: string | null | undefined ='';
  tags: Tags[] = [];

}

class Tags{
  tagName: string = '';
  tagAccuracy: number = 0;
}

