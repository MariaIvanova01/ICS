import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from "@angular/forms";
import { AnalyseService } from "../services/analyse.service";
import { Router } from '@angular/router';
import { tap } from 'rxjs/operators';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.scss']
})
export class HomepageComponent {
  image: Image = new Image();
  imageDimensions: { width: number; height: number } = { width: 0, height: 0 };
  processing = false;
  error: string | null = null;

  imageForm = new FormGroup({
    imageUrl: new FormControl('', [Validators.required, Validators.pattern('^(http|https)://.*$')]),
  });

  constructor(private analyseService: AnalyseService, private router: Router,
              private sanitizer: DomSanitizer) {}

  submit() {
    if (this.imageForm.invalid) {
      return;
    }

    const imageUrl = this.imageForm.get('imageUrl')?.value || '';

    if (!this.isValidUrl(imageUrl)) {
      this.error = 'Invalid URL';
      return;
    }
    this.processing = true;
    this.error = null;

    this.analyseService
      .getImageDimensions(imageUrl)
      .subscribe(
        (dimensions) => {
          this.imageDimensions = dimensions;
          this.analyseService
            .processImage(
              imageUrl,
              this.imageDimensions.width,
              this.imageDimensions.height
            )
            .pipe(
              tap((response) => {
                console.log(response);
                this.image.imageURL = imageUrl;
                this.image.tags = response.tags;
                this.image.date = response.date;
              })
            )
            .subscribe(
              () => {
                this.openImage();
                this.processing = false;
              });
          },
        (error) => {
          console.error('Failed to process image', error);
          this.error = 'Invalid image URL!';
          this.processing = false;
        }
      );
  }
  private isValidUrl(url: string): boolean {
    try {
      new URL(url);
      return true;
    } catch (error) {
      return false;
    }
  }
  openImage() {
    this.router.navigateByUrl('/single-image-view', { state: this.image });
  }
}

class Image {
  imageURL: string | null | undefined = '';
  tags: Tags[] = [];
  date: string = '';
}

class Tags {
  tagName: string = '';
  tagAccuracy: number = 0;
}
