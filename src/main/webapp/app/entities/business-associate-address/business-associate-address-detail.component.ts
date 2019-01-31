import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBusinessAssociateAddress } from 'app/shared/model/business-associate-address.model';

@Component({
    selector: 'jhi-business-associate-address-detail',
    templateUrl: './business-associate-address-detail.component.html'
})
export class BusinessAssociateAddressDetailComponent implements OnInit {
    businessAssociateAddress: IBusinessAssociateAddress;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ businessAssociateAddress }) => {
            this.businessAssociateAddress = businessAssociateAddress;
        });
    }

    previousState() {
        window.history.back();
    }
}
