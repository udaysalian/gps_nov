import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRateSchedVald } from 'app/shared/model/rate-sched-vald.model';

@Component({
    selector: 'jhi-rate-sched-vald-detail',
    templateUrl: './rate-sched-vald-detail.component.html'
})
export class RateSchedValdDetailComponent implements OnInit {
    rateSchedVald: IRateSchedVald;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ rateSchedVald }) => {
            this.rateSchedVald = rateSchedVald;
        });
    }

    previousState() {
        window.history.back();
    }
}
