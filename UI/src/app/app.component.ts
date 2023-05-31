import { HttpService } from './services/http-services.service';
import {Component, OnInit} from '@angular/core';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  title = 'UI';
  posts : any;
  constructor(private httpService: HttpService){}

  ngOnInit() {
    this.httpService.getPosts().subscribe(
      (response) => { this.posts = response; },
      (error) => { console.log(error); });
  }
}
