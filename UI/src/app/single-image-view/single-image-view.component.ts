import {Component} from '@angular/core';
import {SingleImageService} from "../services/single-image.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-single-image-view',
  templateUrl: './single-image-view.component.html',
  styleUrls: ['./single-image-view.component.scss']
})

export class SingleImageViewComponent{
  image;
  constructor(private singleImageService: SingleImageService,
              private router: Router) {
    this.image = this.router.getCurrentNavigation()?.extras?.state;
  }
  submit() {
    this.singleImageService.getImageById()
      .subscribe(images=>{
        console.log(images)
      })
  }
}
