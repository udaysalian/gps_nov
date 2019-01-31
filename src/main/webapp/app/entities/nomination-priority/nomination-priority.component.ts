import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { INominationPriority } from 'app/shared/model/nomination-priority.model';
import { Principal } from 'app/core';
import { NominationPriorityService } from './nomination-priority.service';

@Component({
    selector: 'jhi-nomination-priority',
    templateUrl: './nomination-priority.component.html'
})
export class NominationPriorityComponent implements OnInit, OnDestroy {
    nominationPriorities: INominationPriority[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private nominationPriorityService: NominationPriorityService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.nominationPriorityService.query().subscribe(
            (res: HttpResponse<INominationPriority[]>) => {
                this.nominationPriorities = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInNominationPriorities();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: INominationPriority) {
        return item.id;
    }

    registerChangeInNominationPriorities() {
        this.eventSubscriber = this.eventManager.subscribe('nominationPriorityListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
