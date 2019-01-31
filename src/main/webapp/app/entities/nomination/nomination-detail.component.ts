import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INomination } from 'app/shared/model/nomination.model';

@Component({
    selector: 'jhi-nomination-detail',
    templateUrl: './nomination-detail.component.html'
})
export class NominationDetailComponent implements OnInit {
    nomination: INomination;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ nomination }) => {
            this.nomination = nomination;
        });
    }

    previousState() {
        window.history.back();
    }
}
