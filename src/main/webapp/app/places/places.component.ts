import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'jhi-places',
  templateUrl: './places.component.html',
  styleUrls: [
    'places.scss'
  ]
})
export class PlacesComponent implements OnInit {

  message: string;

  constructor() {
    this.message = 'PlacesComponent message';
  }

  ngOnInit() {
  }

}
