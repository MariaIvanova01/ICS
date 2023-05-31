import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomepageComponent} from "./homepage/homepage.component";
import {SingleImageViewComponent} from "./single-image-view/single-image-view.component";
import {GalleryComponent} from "./gallery/gallery.component";

const routes: Routes = [
  { path: '', redirectTo: '/homepage', pathMatch: "full" },
  { path: 'homepage', component: HomepageComponent },
  { path: 'single-image-view', component: SingleImageViewComponent },
  { path: 'gallery', component: GalleryComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
