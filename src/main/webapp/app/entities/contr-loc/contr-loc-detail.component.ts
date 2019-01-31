import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IContrLoc } from 'app/shared/model/contr-loc.model';

@Component({
    selector: 'jhi-contr-loc-detail',
    templateUrl: './contr-loc-detail.component.html'
})
export class ContrLocDetailComponent implements OnInit {
    contrLoc: IContrLoc;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ contrLoc }) => {
            this.contrLoc = contrLoc;
        });
    }

    previousState() {
        window.history.back();
    }
}
