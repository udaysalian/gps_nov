import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IBusinessAssociate } from 'app/shared/model/business-associate.model';
import { BusinessAssociateService } from './business-associate.service';

@Component({
    selector: 'jhi-business-associate-update',
    templateUrl: './business-associate-update.component.html'
})
export class BusinessAssociateUpdateComponent implements OnInit {
    private _businessAssociate: IBusinessAssociate;
    isSaving: boolean;

    constructor(private businessAssociateService: BusinessAssociateService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ businessAssociate }) => {
            this.businessAssociate = businessAssociate;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.businessAssociate.id !== undefined) {
            this.subscribeToSaveResponse(this.businessAssociateService.update(this.businessAssociate));
        } else {
            this.subscribeToSaveResponse(this.businessAssociateService.create(this.businessAssociate));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IBusinessAssociate>>) {
        result.subscribe((res: HttpResponse<IBusinessAssociate>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get businessAssociate() {
        return this._businessAssociate;
    }

    set businessAssociate(businessAssociate: IBusinessAssociate) {
        this._businessAssociate = businessAssociate;
    }
}
