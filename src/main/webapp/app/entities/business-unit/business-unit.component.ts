import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IBusinessUnit } from 'app/shared/model/business-unit.model';
import { Principal } from 'app/core';
import { BusinessUnitService } from './business-unit.service';

@Component({
    selector: 'jhi-business-unit',
    templateUrl: './business-unit.component.html'
})
export class BusinessUnitComponent implements OnInit, OnDestroy {
    businessUnits: IBusinessUnit[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private businessUnitService: BusinessUnitService,
        private jhiAlertService: JhiAlertService,
        private dataUtils: JhiDataUtils,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.businessUnitService.query().subscribe(
            (res: HttpResponse<IBusinessUnit[]>) => {
                this.businessUnits = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInBusinessUnits();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IBusinessUnit) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInBusinessUnits() {
        this.eventSubscriber = this.eventManager.subscribe('businessUnitListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
