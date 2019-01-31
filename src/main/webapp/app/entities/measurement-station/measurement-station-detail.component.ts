import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMeasurementStation } from 'app/shared/model/measurement-station.model';

@Component({
    selector: 'jhi-measurement-station-detail',
    templateUrl: './measurement-station-detail.component.html'
})
export class MeasurementStationDetailComponent implements OnInit {
    measurementStation: IMeasurementStation;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ measurementStation }) => {
            this.measurementStation = measurementStation;
        });
    }

    previousState() {
        window.history.back();
    }
}
