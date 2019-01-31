import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBusinessAssociate } from 'app/shared/model/business-associate.model';

@Component({
    selector: 'jhi-business-associate-detail',
    templateUrl: './business-associate-detail.component.html'
})
export class BusinessAssociateDetailComponent implements OnInit {
    businessAssociate: IBusinessAssociate;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ businessAssociate }) => {
            this.businessAssociate = businessAssociate;
        });
    }

    previousState() {
        window.history.back();
    }
}
