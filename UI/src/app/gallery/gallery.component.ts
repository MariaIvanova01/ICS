import {Component, OnInit} from '@angular/core';
import {GalleryService} from "../services/gallery.service";

@Component({
  selector: 'app-gallery',
  templateUrl: './gallery.component.html',
  styleUrls: ['./gallery.component.scss']
})
export class GalleryComponent implements OnInit{
  constructor(private galleryService: GalleryService) {
  }
  ngOnInit() {
    this.galleryService.getAllImages()
      .subscribe(images=>{
      console.log(images);
    })
  }
}
