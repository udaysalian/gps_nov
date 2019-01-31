import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IRateSched } from 'app/shared/model/rate-sched.model';
import { RateSchedService } from './rate-sched.service';

@Component({
    selector: 'jhi-rate-sched-update',
    templateUrl: './rate-sched-update.component.html'
})
export class RateSchedUpdateComponent implements OnInit {
    private _rateSched: IRateSched;
    isSaving: boolean;
    updateTimeStamp: string;

    constructor(private rateSchedService: RateSchedService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ rateSched }) => {
            this.rateSched = rateSched;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.rateSched.updateTimeStamp = moment(this.updateTimeStamp, DATE_TIME_FORMAT);
        if (this.rateSched.id !== undefined) {
            this.subscribeToSaveResponse(this.rateSchedService.update(this.rateSched));
        } else {
            this.subscribeToSaveResponse(this.rateSchedService.create(this.rateSched));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRateSched>>) {
        result.subscribe((res: HttpResponse<IRateSched>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get rateSched() {
        return this._rateSched;
    }

    set rateSched(rateSched: IRateSched) {
        this._rateSched = rateSched;
        this.updateTimeStamp = moment(rateSched.updateTimeStamp).format(DATE_TIME_FORMAT);
    }
}
