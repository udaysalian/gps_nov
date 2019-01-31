import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INominationPriority } from 'app/shared/model/nomination-priority.model';

@Component({
    selector: 'jhi-nomination-priority-detail',
    templateUrl: './nomination-priority-detail.component.html'
})
export class NominationPriorityDetailComponent implements OnInit {
    nominationPriority: INominationPriority;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ nominationPriority }) => {
            this.nominationPriority = nominationPriority;
        });
    }

    previousState() {
        window.history.back();
    }
}
