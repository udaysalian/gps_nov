import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IReductionReason } from 'app/shared/model/reduction-reason.model';

@Component({
    selector: 'jhi-reduction-reason-detail',
    templateUrl: './reduction-reason-detail.component.html'
})
export class ReductionReasonDetailComponent implements OnInit {
    reductionReason: IReductionReason;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ reductionReason }) => {
            this.reductionReason = reductionReason;
        });
    }

    previousState() {
        window.history.back();
    }
}
