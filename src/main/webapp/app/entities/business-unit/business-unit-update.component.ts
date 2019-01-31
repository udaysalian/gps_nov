import { Component, OnInit, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiDataUtils } from 'ng-jhipster';

import { IBusinessUnit } from 'app/shared/model/business-unit.model';
import { BusinessUnitService } from './business-unit.service';

@Component({
    selector: 'jhi-business-unit-update',
    templateUrl: './business-unit-update.component.html'
})
export class BusinessUnitUpdateComponent implements OnInit {
    private _businessUnit: IBusinessUnit;
    isSaving: boolean;
    updateTimestamp: string;

    constructor(
        private dataUtils: JhiDataUtils,
        private businessUnitService: BusinessUnitService,
        private elementRef: ElementRef,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ businessUnit }) => {
            this.businessUnit = businessUnit;
        });
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    clearInputImage(field: string, fieldContentType: string, idInput: string) {
        this.dataUtils.clearInputImage(this.businessUnit, this.elementRef, field, fieldContentType, idInput);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.businessUnit.updateTimestamp = moment(this.updateTimestamp, DATE_TIME_FORMAT);
        if (this.businessUnit.id !== undefined) {
            this.subscribeToSaveResponse(this.businessUnitService.update(this.businessUnit));
        } else {
            this.subscribeToSaveResponse(this.businessUnitService.create(this.businessUnit));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IBusinessUnit>>) {
        result.subscribe((res: HttpResponse<IBusinessUnit>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get businessUnit() {
        return this._businessUnit;
    }

    set businessUnit(businessUnit: IBusinessUnit) {
        this._businessUnit = businessUnit;
        this.updateTimestamp = moment(businessUnit.updateTimestamp).format(DATE_TIME_FORMAT);
    }
}
