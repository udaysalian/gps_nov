import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ILocationBA } from 'app/shared/model/location-ba.model';
import { Principal } from 'app/core';
import { LocationBAService } from './location-ba.service';

@Component({
    selector: 'jhi-location-ba',
    templateUrl: './location-ba.component.html'
})
export class LocationBAComponent implements OnInit, OnDestroy {
    locationBAS: ILocationBA[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private locationBAService: LocationBAService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.locationBAService.query().subscribe(
            (res: HttpResponse<ILocationBA[]>) => {
                this.locationBAS = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInLocationBAS();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ILocationBA) {
        return item.id;
    }

    registerChangeInLocationBAS() {
        this.eventSubscriber = this.eventManager.subscribe('locationBAListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
