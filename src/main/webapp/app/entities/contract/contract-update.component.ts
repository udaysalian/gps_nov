import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IContract } from 'app/shared/model/contract.model';
import { ContractService } from './contract.service';
import { IRateSched } from 'app/shared/model/rate-sched.model';
import { RateSchedService } from 'app/entities/rate-sched';
import { IBusinessAssociate } from 'app/shared/model/business-associate.model';
import { BusinessAssociateService } from 'app/entities/business-associate';

@Component({
    selector: 'jhi-contract-update',
    templateUrl: './contract-update.component.html'
})
export class ContractUpdateComponent implements OnInit {
    private _contract: IContract;
    isSaving: boolean;

    ratescheds: IRateSched[];

    businessassociates: IBusinessAssociate[];
    updateTimeStamp: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private contractService: ContractService,
        private rateSchedService: RateSchedService,
        private businessAssociateService: BusinessAssociateService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ contract }) => {
            this.contract = contract;
        });
        this.rateSchedService.query().subscribe(
            (res: HttpResponse<IRateSched[]>) => {
                this.ratescheds = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.businessAssociateService.query().subscribe(
            (res: HttpResponse<IBusinessAssociate[]>) => {
                this.businessassociates = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.contract.updateTimeStamp = moment(this.updateTimeStamp, DATE_TIME_FORMAT);
        if (this.contract.id !== undefined) {
            this.subscribeToSaveResponse(this.contractService.update(this.contract));
        } else {
            this.subscribeToSaveResponse(this.contractService.create(this.contract));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IContract>>) {
        result.subscribe((res: HttpResponse<IContract>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackBusinessAssociateById(index: number, item: IBusinessAssociate) {
        return item.id;
    }
    get contract() {
        return this._contract;
    }

    set contract(contract: IContract) {
        this._contract = contract;
        this.updateTimeStamp = moment(contract.updateTimeStamp).format(DATE_TIME_FORMAT);
    }
}
