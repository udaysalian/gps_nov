import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IContrLoc } from 'app/shared/model/contr-loc.model';
import { ContrLocService } from './contr-loc.service';
import { IContract } from 'app/shared/model/contract.model';
import { ContractService } from 'app/entities/contract';
import { IMeasurementStation } from 'app/shared/model/measurement-station.model';
import { MeasurementStationService } from 'app/entities/measurement-station';

@Component({
    selector: 'jhi-contr-loc-update',
    templateUrl: './contr-loc-update.component.html'
})
export class ContrLocUpdateComponent implements OnInit {
    private _contrLoc: IContrLoc;
    isSaving: boolean;

    contracts: IContract[];

    locations: IMeasurementStation[];
    effectiveFromDateDp: any;
    effectiveToDateDp: any;
    updateTimeStamp: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private contrLocService: ContrLocService,
        private contractService: ContractService,
        private measurementStationService: MeasurementStationService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ contrLoc }) => {
            this.contrLoc = contrLoc;
        });
        this.contractService.query().subscribe(
            (res: HttpResponse<IContract[]>) => {
                this.contracts = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.measurementStationService.query({ filter: 'contrloc-is-null' }).subscribe(
            (res: HttpResponse<IMeasurementStation[]>) => {
                if (!this.contrLoc.locationId) {
                    this.locations = res.body;
                } else {
                    this.measurementStationService.find(this.contrLoc.locationId).subscribe(
                        (subRes: HttpResponse<IMeasurementStation>) => {
                            this.locations = [subRes.body].concat(res.body);
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
        this.contrLoc.updateTimeStamp = moment(this.updateTimeStamp, DATE_TIME_FORMAT);
        if (this.contrLoc.id !== undefined) {
            this.subscribeToSaveResponse(this.contrLocService.update(this.contrLoc));
        } else {
            this.subscribeToSaveResponse(this.contrLocService.create(this.contrLoc));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IContrLoc>>) {
        result.subscribe((res: HttpResponse<IContrLoc>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackContractById(index: number, item: IContract) {
        return item.id;
    }

    trackMeasurementStationById(index: number, item: IMeasurementStation) {
        return item.id;
    }
    get contrLoc() {
        return this._contrLoc;
    }

    set contrLoc(contrLoc: IContrLoc) {
        this._contrLoc = contrLoc;
        this.updateTimeStamp = moment(contrLoc.updateTimeStamp).format(DATE_TIME_FORMAT);
    }
}
