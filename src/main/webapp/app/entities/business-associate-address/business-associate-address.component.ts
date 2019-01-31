import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IBusinessAssociateAddress } from 'app/shared/model/business-associate-address.model';
import { Principal } from 'app/core';
import { BusinessAssociateAddressService } from './business-associate-address.service';

@Component({
    selector: 'jhi-business-associate-address',
    templateUrl: './business-associate-address.component.html'
})
export class BusinessAssociateAddressComponent implements OnInit, OnDestroy {
    businessAssociateAddresses: IBusinessAssociateAddress[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private businessAssociateAddressService: BusinessAssociateAddressService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.businessAssociateAddressService.query().subscribe(
            (res: HttpResponse<IBusinessAssociateAddress[]>) => {
                this.businessAssociateAddresses = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInBusinessAssociateAddresses();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IBusinessAssociateAddress) {
        return item.id;
    }

    registerChangeInBusinessAssociateAddresses() {
        this.eventSubscriber = this.eventManager.subscribe('businessAssociateAddressListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
