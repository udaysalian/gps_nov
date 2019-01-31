import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBusinessAssociateContact } from 'app/shared/model/business-associate-contact.model';

@Component({
    selector: 'jhi-business-associate-contact-detail',
    templateUrl: './business-associate-contact-detail.component.html'
})
export class BusinessAssociateContactDetailComponent implements OnInit {
    businessAssociateContact: IBusinessAssociateContact;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ businessAssociateContact }) => {
            this.businessAssociateContact = businessAssociateContact;
        });
    }

    previousState() {
        window.history.back();
    }
}
