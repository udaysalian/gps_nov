import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IBusinessAssociateAddress } from 'app/shared/model/business-associate-address.model';
import { BusinessAssociateAddressService } from './business-associate-address.service';
import { IBusinessAssociate } from 'app/shared/model/business-associate.model';
import { BusinessAssociateService } from 'app/entities/business-associate';

@Component({
    selector: 'jhi-business-associate-address-update',
    templateUrl: './business-associate-address-update.component.html'
})
export class BusinessAssociateAddressUpdateComponent implements OnInit {
    private _businessAssociateAddress: IBusinessAssociateAddress;
    isSaving: boolean;

    businessassociates: IBusinessAssociate[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private businessAssociateAddressService: BusinessAssociateAddressService,
        private businessAssociateService: BusinessAssociateService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ businessAssociateAddress }) => {
            this.businessAssociateAddress = businessAssociateAddress;
        });
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
        if (this.businessAssociateAddress.id !== undefined) {
            this.subscribeToSaveResponse(this.businessAssociateAddressService.update(this.businessAssociateAddress));
        } else {
            this.subscribeToSaveResponse(this.businessAssociateAddressService.create(this.businessAssociateAddress));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IBusinessAssociateAddress>>) {
        result.subscribe(
            (res: HttpResponse<IBusinessAssociateAddress>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
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
    get businessAssociateAddress() {
        return this._businessAssociateAddress;
    }

    set businessAssociateAddress(businessAssociateAddress: IBusinessAssociateAddress) {
        this._businessAssociateAddress = businessAssociateAddress;
    }
}
