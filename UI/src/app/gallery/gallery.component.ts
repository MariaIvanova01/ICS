import {Component, OnInit, ViewChild, ElementRef} from '@angular/core';
import {GalleryService} from "../services/gallery.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-gallery',
  templateUrl: './gallery.component.html',
  styleUrls: ['./gallery.component.scss']
})
export class GalleryComponent implements OnInit{
  images:Image[] = [];
  searchTags: string = '';
  @ViewChild('searchButton', { static: false }) searchButton: ElementRef;

  constructor(private galleryService: GalleryService,
              private router: Router) {
    this.searchButton = {} as ElementRef;
  }
  ngOnInit() {
    this.galleryService.getAllImages()
      .subscribe((images: Image[])=>{
        this.images = images;
    })
  }
  openImage(image: Image){
    this.router.navigateByUrl('/single-image-view',{ state: image});
  }

  tagSearch(){
    let tagsArray: string[] = this.searchTags.split(", ");
    this.galleryService.getImageByTags(tagsArray)
      .subscribe((images: Image[])=> {
        this.images = images;
        console.log(this.images)
      })
  }
}
class Image{
  imageURL: string;
  tags: Tags[];

  constructor(image: Image){
    this.imageURL = image.imageURL;
    this.tags = image.tags;
  }
}

class Tags{
  tagName: string = '';
  tagAccuracy: number = 0;
}
