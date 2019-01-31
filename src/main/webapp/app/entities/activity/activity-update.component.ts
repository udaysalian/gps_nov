import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IActivity } from 'app/shared/model/activity.model';
import { ActivityService } from './activity.service';
import { IBusinessAssociate } from 'app/shared/model/business-associate.model';
import { BusinessAssociateService } from 'app/entities/business-associate';
import { IRateSched } from 'app/shared/model/rate-sched.model';
import { RateSchedService } from 'app/entities/rate-sched';
import { IMeasurementStation } from 'app/shared/model/measurement-station.model';
import { MeasurementStationService } from 'app/entities/measurement-station';
import { IContract } from 'app/shared/model/contract.model';
import { ContractService } from 'app/entities/contract';

@Component({
    selector: 'jhi-activity-update',
    templateUrl: './activity-update.component.html'
})
export class ActivityUpdateComponent implements OnInit {
    private _activity: IActivity;
    isSaving: boolean;

    upstreambas: IBusinessAssociate[];

    downstreambas: IBusinessAssociate[];

    ratescheds: IRateSched[];

    measurementstations: IMeasurementStation[];

    contracts: IContract[];
    updateTimeStamp: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private activityService: ActivityService,
        private businessAssociateService: BusinessAssociateService,
        private rateSchedService: RateSchedService,
        private measurementStationService: MeasurementStationService,
        private contractService: ContractService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ activity }) => {
            this.activity = activity;
        });
        this.businessAssociateService.query({ filter: 'activity-is-null' }).subscribe(
            (res: HttpResponse<IBusinessAssociate[]>) => {
                if (!this.activity.upstreamBAId) {
                    this.upstreambas = res.body;
                } else {
                    this.businessAssociateService.find(this.activity.upstreamBAId).subscribe(
                        (subRes: HttpResponse<IBusinessAssociate>) => {
                            this.upstreambas = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.businessAssociateService.query({ filter: 'activity-is-null' }).subscribe(
            (res: HttpResponse<IBusinessAssociate[]>) => {
                if (!this.activity.downstreamBAId) {
                    this.downstreambas = res.body;
                } else {
                    this.businessAssociateService.find(this.activity.downstreamBAId).subscribe(
                        (subRes: HttpResponse<IBusinessAssociate>) => {
                            this.downstreambas = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.rateSchedService.query().subscribe(
            (res: HttpResponse<IRateSched[]>) => {
                this.ratescheds = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.measurementStationService.query().subscribe(
            (res: HttpResponse<IMeasurementStation[]>) => {
                this.measurementstations = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.contractService.query().subscribe(
            (res: HttpResponse<IContract[]>) => {
                this.contracts = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.activity.updateTimeStamp = moment(this.updateTimeStamp, DATE_TIME_FORMAT);
        if (this.activity.id !== undefined) {
            this.subscribeToSaveResponse(this.activityService.update(this.activity));
        } else {
            this.subscribeToSaveResponse(this.activityService.create(this.activity));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IActivity>>) {
        result.subscribe((res: HttpResponse<IActivity>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackBusinessAssociateById(index: number, item: IBusinessAssociate) {
        return item.id;
    }

    trackRateSchedById(index: number, item: IRateSched) {
        return item.id;
    }

    trackMeasurementStationById(index: number, item: IMeasurementStation) {
        return item.id;
    }

    trackContractById(index: number, item: IContract) {
        return item.id;
    }
    get activity() {
        return this._activity;
    }

    set activity(activity: IActivity) {
        this._activity = activity;
        this.updateTimeStamp = moment(activity.updateTimeStamp).format(DATE_TIME_FORMAT);
    }
}
