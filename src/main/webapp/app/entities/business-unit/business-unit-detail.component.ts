import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IBusinessUnit } from 'app/shared/model/business-unit.model';

@Component({
    selector: 'jhi-business-unit-detail',
    templateUrl: './business-unit-detail.component.html'
})
export class BusinessUnitDetailComponent implements OnInit {
    businessUnit: IBusinessUnit;

    constructor(private dataUtils: JhiDataUtils, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
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
    previousState() {
        window.history.back();
    }
}
