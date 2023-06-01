import { Component } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AnalyseService} from "../services/analyse.service";

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.scss']
})
export class HomepageComponent {
  imageForm = new FormGroup({
    imageUrl: new FormControl('', Validators.required),
  });
  constructor(private analyseService: AnalyseService) {
  }

  submit(imageUrl: any){
    console.log(imageUrl)
    this.analyseService.processImage(imageUrl.value.imageUrl, 200,200)
      .subscribe(response => {
      console.log(response);
    })
  }

}
