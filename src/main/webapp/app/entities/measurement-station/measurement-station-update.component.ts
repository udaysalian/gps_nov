import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IMeasurementStation } from 'app/shared/model/measurement-station.model';
import { MeasurementStationService } from './measurement-station.service';

@Component({
    selector: 'jhi-measurement-station-update',
    templateUrl: './measurement-station-update.component.html'
})
export class MeasurementStationUpdateComponent implements OnInit {
    private _measurementStation: IMeasurementStation;
    isSaving: boolean;
    updateTimestamp: string;

    constructor(private measurementStationService: MeasurementStationService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ measurementStation }) => {
            this.measurementStation = measurementStation;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.measurementStation.updateTimestamp = moment(this.updateTimestamp, DATE_TIME_FORMAT);
        if (this.measurementStation.id !== undefined) {
            this.subscribeToSaveResponse(this.measurementStationService.update(this.measurementStation));
        } else {
            this.subscribeToSaveResponse(this.measurementStationService.create(this.measurementStation));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IMeasurementStation>>) {
        result.subscribe((res: HttpResponse<IMeasurementStation>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get measurementStation() {
        return this._measurementStation;
    }

    set measurementStation(measurementStation: IMeasurementStation) {
        this._measurementStation = measurementStation;
        this.updateTimestamp = moment(measurementStation.updateTimestamp).format(DATE_TIME_FORMAT);
    }
}
