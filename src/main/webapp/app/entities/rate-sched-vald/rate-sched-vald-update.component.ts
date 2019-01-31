import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IRateSchedVald } from 'app/shared/model/rate-sched-vald.model';
import { RateSchedValdService } from './rate-sched-vald.service';
import { IRateSched } from 'app/shared/model/rate-sched.model';
import { RateSchedService } from 'app/entities/rate-sched';

@Component({
    selector: 'jhi-rate-sched-vald-update',
    templateUrl: './rate-sched-vald-update.component.html'
})
export class RateSchedValdUpdateComponent implements OnInit {
    private _rateSchedVald: IRateSchedVald;
    isSaving: boolean;

    ratescheds: IRateSched[];
    updateTimeStamp: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private rateSchedValdService: RateSchedValdService,
        private rateSchedService: RateSchedService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ rateSchedVald }) => {
            this.rateSchedVald = rateSchedVald;
        });
        this.rateSchedService.query().subscribe(
            (res: HttpResponse<IRateSched[]>) => {
                this.ratescheds = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.rateSchedVald.updateTimeStamp = moment(this.updateTimeStamp, DATE_TIME_FORMAT);
        if (this.rateSchedVald.id !== undefined) {
            this.subscribeToSaveResponse(this.rateSchedValdService.update(this.rateSchedVald));
        } else {
            this.subscribeToSaveResponse(this.rateSchedValdService.create(this.rateSchedVald));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRateSchedVald>>) {
        result.subscribe((res: HttpResponse<IRateSchedVald>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackRateSchedById(index: number, item: IRateSched) {
        return item.id;
    }
    get rateSchedVald() {
        return this._rateSchedVald;
    }

    set rateSchedVald(rateSchedVald: IRateSchedVald) {
        this._rateSchedVald = rateSchedVald;
        this.updateTimeStamp = moment(rateSchedVald.updateTimeStamp).format(DATE_TIME_FORMAT);
    }
}
