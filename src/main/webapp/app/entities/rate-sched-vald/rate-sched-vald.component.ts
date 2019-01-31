import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IRateSchedVald } from 'app/shared/model/rate-sched-vald.model';
import { Principal } from 'app/core';
import { RateSchedValdService } from './rate-sched-vald.service';

@Component({
    selector: 'jhi-rate-sched-vald',
    templateUrl: './rate-sched-vald.component.html'
})
export class RateSchedValdComponent implements OnInit, OnDestroy {
    rateSchedValds: IRateSchedVald[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private rateSchedValdService: RateSchedValdService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.rateSchedValdService.query().subscribe(
            (res: HttpResponse<IRateSchedVald[]>) => {
                this.rateSchedValds = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInRateSchedValds();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IRateSchedVald) {
        return item.id;
    }

    registerChangeInRateSchedValds() {
        this.eventSubscriber = this.eventManager.subscribe('rateSchedValdListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
