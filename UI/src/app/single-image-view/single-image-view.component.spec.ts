import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SingleImageViewComponent } from './single-image-view.component';

describe('SingleImageViewComponent', () => {
  let component: SingleImageViewComponent;
  let fixture: ComponentFixture<SingleImageViewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SingleImageViewComponent]
    });
    fixture = TestBed.createComponent(SingleImageViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
