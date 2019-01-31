import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRateSched } from 'app/shared/model/rate-sched.model';

@Component({
    selector: 'jhi-rate-sched-detail',
    templateUrl: './rate-sched-detail.component.html'
})
export class RateSchedDetailComponent implements OnInit {
    rateSched: IRateSched;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ rateSched }) => {
            this.rateSched = rateSched;
        });
    }

    previousState() {
        window.history.back();
    }
}
