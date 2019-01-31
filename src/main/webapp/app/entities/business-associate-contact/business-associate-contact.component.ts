import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IBusinessAssociateContact } from 'app/shared/model/business-associate-contact.model';
import { Principal } from 'app/core';
import { BusinessAssociateContactService } from './business-associate-contact.service';

@Component({
    selector: 'jhi-business-associate-contact',
    templateUrl: './business-associate-contact.component.html'
})
export class BusinessAssociateContactComponent implements OnInit, OnDestroy {
    businessAssociateContacts: IBusinessAssociateContact[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private businessAssociateContactService: BusinessAssociateContactService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.businessAssociateContactService.query().subscribe(
            (res: HttpResponse<IBusinessAssociateContact[]>) => {
                this.businessAssociateContacts = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInBusinessAssociateContacts();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IBusinessAssociateContact) {
        return item.id;
    }

    registerChangeInBusinessAssociateContacts() {
        this.eventSubscriber = this.eventManager.subscribe('businessAssociateContactListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
