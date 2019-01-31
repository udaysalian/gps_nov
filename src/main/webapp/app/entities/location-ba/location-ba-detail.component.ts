import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILocationBA } from 'app/shared/model/location-ba.model';

@Component({
    selector: 'jhi-location-ba-detail',
    templateUrl: './location-ba-detail.component.html'
})
export class LocationBADetailComponent implements OnInit {
    locationBA: ILocationBA;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ locationBA }) => {
            this.locationBA = locationBA;
        });
    }

    previousState() {
        window.history.back();
    }
}
