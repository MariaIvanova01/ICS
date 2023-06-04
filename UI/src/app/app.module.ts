import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomepageComponent } from './homepage/homepage.component';
import { SingleImageViewComponent } from './single-image-view/single-image-view.component';
import { GalleryComponent } from './gallery/gallery.component';
import {HttpClientModule} from "@angular/common/http";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {ClarityModule} from "@clr/angular";
import {FormsModule} from "@angular/forms";
import {ReactiveFormsModule} from "@angular/forms";
import {HttpService} from "./services/http-services.service";
import '@cds/core/icon/register.js';
/*import { ClarityIcons, searchIcon } from '@cds/core/icon';
ClarityIcons.addIcons(searchIcon)*/

@NgModule({
  declarations: [
    AppComponent,
    HomepageComponent,
    SingleImageViewComponent,
    GalleryComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ClarityModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  providers: [
    HttpService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
