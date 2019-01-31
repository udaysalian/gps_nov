import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { INomination } from 'app/shared/model/nomination.model';
import { NominationService } from './netraNomination.service';
import { IActivity } from 'app/shared/model/activity.model';
import { ActivityService } from 'app/entities/activity';
import { IContract } from 'app/shared/model/contract.model';
import { ContractService } from 'app/entities/contract';

@Component({
    selector: 'jhi-nomination-update',
    templateUrl: './nomination-update.component.html'
})
export class NominationUpdateComponent implements OnInit {
    private _nomination: INomination;
    isSaving: boolean;

    activities: IActivity[];

    contracts: IContract[];
    gasDateDp: any;
    updateTimeStamp: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private nominationService: NominationService,
        private activityService: ActivityService,
        private contractService: ContractService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ nomination }) => {
            this.nomination = nomination;
        });
        this.activityService.query({ filter: 'nomination-is-null' }).subscribe(
            (res: HttpResponse<IActivity[]>) => {
                if (!this.nomination.activityId) {
                    this.activities = res.body;
                } else {
                    this.activityService.find(this.nomination.activityId).subscribe(
                        (subRes: HttpResponse<IActivity>) => {
                            this.activities = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.contractService.query({ filter: 'nomination-is-null' }).subscribe(
            (res: HttpResponse<IContract[]>) => {
                if (!this.nomination.contractId) {
                    this.contracts = res.body;
                } else {
                    this.contractService.find(this.nomination.contractId).subscribe(
                        (subRes: HttpResponse<IContract>) => {
                            this.contracts = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.nomination.updateTimeStamp = moment(this.updateTimeStamp, DATE_TIME_FORMAT);
        if (this.nomination.id !== undefined) {
            this.subscribeToSaveResponse(this.nominationService.update(this.nomination));
        } else {
            this.subscribeToSaveResponse(this.nominationService.create(this.nomination));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<INomination>>) {
        result.subscribe((res: HttpResponse<INomination>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackActivityById(index: number, item: IActivity) {
        return item.id;
    }

    trackContractById(index: number, item: IContract) {
        return item.id;
    }
    get nomination() {
        return this._nomination;
    }

    set nomination(nomination: INomination) {
        this._nomination = nomination;
        this.updateTimeStamp = moment(nomination.updateTimeStamp).format(DATE_TIME_FORMAT);
    }
}
