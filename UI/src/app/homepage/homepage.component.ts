import { Component } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {HttpService} from "../services/http-services.service";

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.scss']
})
export class HomepageComponent {
  imageForm = new FormGroup({
    imageUrl: new FormControl('DEFAULT', Validators.required),
  });

  submit(){
    console.log(this.imageForm.getRawValue());

    this.imageForm.setValue({
      imageUrl: "YEAH"
    })
  }

}
