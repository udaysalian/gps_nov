import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { INominationPriority } from 'app/shared/model/nomination-priority.model';
import { NominationPriorityService } from './nomination-priority.service';
import { INomination } from 'app/shared/model/nomination.model';
import { NominationService } from 'app/entities/nomination';
import { IActivity } from 'app/shared/model/activity.model';
import { ActivityService } from 'app/entities/activity';
import { IContract } from 'app/shared/model/contract.model';
import { ContractService } from 'app/entities/contract';

@Component({
    selector: 'jhi-nomination-priority-update',
    templateUrl: './nomination-priority-update.component.html'
})
export class NominationPriorityUpdateComponent implements OnInit {
    private _nominationPriority: INominationPriority;
    isSaving: boolean;

    nominations: INomination[];

    activities: IActivity[];

    contracts: IContract[];
    gasDateDp: any;
    updateTimeStamp: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private nominationPriorityService: NominationPriorityService,
        private nominationService: NominationService,
        private activityService: ActivityService,
        private contractService: ContractService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ nominationPriority }) => {
            this.nominationPriority = nominationPriority;
        });
        this.nominationService.query().subscribe(
            (res: HttpResponse<INomination[]>) => {
                this.nominations = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.activityService.query({ filter: 'nominationpriority-is-null' }).subscribe(
            (res: HttpResponse<IActivity[]>) => {
                if (!this.nominationPriority.activityId) {
                    this.activities = res.body;
                } else {
                    this.activityService.find(this.nominationPriority.activityId).subscribe(
                        (subRes: HttpResponse<IActivity>) => {
                            this.activities = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.contractService.query({ filter: 'nominationpriority-is-null' }).subscribe(
            (res: HttpResponse<IContract[]>) => {
                if (!this.nominationPriority.contractId) {
                    this.contracts = res.body;
                } else {
                    this.contractService.find(this.nominationPriority.contractId).subscribe(
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
        this.nominationPriority.updateTimeStamp = moment(this.updateTimeStamp, DATE_TIME_FORMAT);
        if (this.nominationPriority.id !== undefined) {
            this.subscribeToSaveResponse(this.nominationPriorityService.update(this.nominationPriority));
        } else {
            this.subscribeToSaveResponse(this.nominationPriorityService.create(this.nominationPriority));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<INominationPriority>>) {
        result.subscribe((res: HttpResponse<INominationPriority>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackNominationById(index: number, item: INomination) {
        return item.id;
    }

    trackActivityById(index: number, item: IActivity) {
        return item.id;
    }

    trackContractById(index: number, item: IContract) {
        return item.id;
    }
    get nominationPriority() {
        return this._nominationPriority;
    }

    set nominationPriority(nominationPriority: INominationPriority) {
        this._nominationPriority = nominationPriority;
        this.updateTimeStamp = moment(nominationPriority.updateTimeStamp).format(DATE_TIME_FORMAT);
    }
}
