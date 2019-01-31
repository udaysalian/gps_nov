import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { ILocationBA } from 'app/shared/model/location-ba.model';
import { LocationBAService } from './location-ba.service';
import { IBusinessAssociate } from 'app/shared/model/business-associate.model';
import { BusinessAssociateService } from 'app/entities/business-associate';

@Component({
    selector: 'jhi-location-ba-update',
    templateUrl: './location-ba-update.component.html'
})
export class LocationBAUpdateComponent implements OnInit {
    private _locationBA: ILocationBA;
    isSaving: boolean;

    busassocs: IBusinessAssociate[];
    updateTimestamp: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private locationBAService: LocationBAService,
        private businessAssociateService: BusinessAssociateService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ locationBA }) => {
            this.locationBA = locationBA;
        });
        this.businessAssociateService.query({ filter: 'locationba-is-null' }).subscribe(
            (res: HttpResponse<IBusinessAssociate[]>) => {
                if (!this.locationBA.busAssocId) {
                    this.busassocs = res.body;
                } else {
                    this.businessAssociateService.find(this.locationBA.busAssocId).subscribe(
                        (subRes: HttpResponse<IBusinessAssociate>) => {
                            this.busassocs = [subRes.body].concat(res.body);
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
        this.locationBA.updateTimestamp = moment(this.updateTimestamp, DATE_TIME_FORMAT);
        if (this.locationBA.id !== undefined) {
            this.subscribeToSaveResponse(this.locationBAService.update(this.locationBA));
        } else {
            this.subscribeToSaveResponse(this.locationBAService.create(this.locationBA));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ILocationBA>>) {
        result.subscribe((res: HttpResponse<ILocationBA>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
    get locationBA() {
        return this._locationBA;
    }

    set locationBA(locationBA: ILocationBA) {
        this._locationBA = locationBA;
        this.updateTimestamp = moment(locationBA.updateTimestamp).format(DATE_TIME_FORMAT);
    }
}
