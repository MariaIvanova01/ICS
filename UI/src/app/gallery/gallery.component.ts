import {Component, OnInit, ViewChild, ElementRef} from '@angular/core';
import {GalleryService} from "../services/gallery.service";
import {Router,ActivatedRoute} from "@angular/router";

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
              private router: Router, private route:ActivatedRoute) {
    this.searchButton = {} as ElementRef;
  }
  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      if (params['searchTags']) {
        const tagsArray: string[] = (params['searchTags'] as string).split(',').map((tag: string) => tag.trim());
        this.filterImages(tagsArray);
      } else {
        this.galleryService.getAllImages()
          .subscribe((images: Image[])=>{
            this.images = images;
          })
      }
    });
  }
  openImage(image: Image){
    this.router.navigateByUrl('/single-image-view',{ state: image});
  }

  filterImages(tags: string[]) {
    this.galleryService.getImageByTags(tags).subscribe((images: Image[]) => {
      this.images = images;
      console.log(this.images);
    });
  }
  tagSearch(){
    this.router.navigate(['/gallery'], { queryParams: { searchTags: this.searchTags } });
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

  constructor(tagName: string, tagAccuracy: number) {
    this.tagName = tagName;
    this.tagAccuracy = tagAccuracy;
  }
}
