import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IReductionReason } from 'app/shared/model/reduction-reason.model';
import { Principal } from 'app/core';
import { ReductionReasonService } from './reduction-reason.service';

@Component({
    selector: 'jhi-reduction-reason',
    templateUrl: './reduction-reason.component.html'
})
export class ReductionReasonComponent implements OnInit, OnDestroy {
    reductionReasons: IReductionReason[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private reductionReasonService: ReductionReasonService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.reductionReasonService.query().subscribe(
            (res: HttpResponse<IReductionReason[]>) => {
                this.reductionReasons = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInReductionReasons();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IReductionReason) {
        return item.id;
    }

    registerChangeInReductionReasons() {
        this.eventSubscriber = this.eventManager.subscribe('reductionReasonListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
