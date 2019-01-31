import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IContrLoc } from 'app/shared/model/contr-loc.model';
import { Principal } from 'app/core';
import { ContrLocService } from './contr-loc.service';

@Component({
    selector: 'jhi-contr-loc',
    templateUrl: './contr-loc.component.html'
})
export class ContrLocComponent implements OnInit, OnDestroy {
    contrLocs: IContrLoc[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private contrLocService: ContrLocService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.contrLocService.query().subscribe(
            (res: HttpResponse<IContrLoc[]>) => {
                this.contrLocs = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInContrLocs();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IContrLoc) {
        return item.id;
    }

    registerChangeInContrLocs() {
        this.eventSubscriber = this.eventManager.subscribe('contrLocListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
