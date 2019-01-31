import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IReductionReason } from 'app/shared/model/reduction-reason.model';
import { ReductionReasonService } from './reduction-reason.service';

@Component({
    selector: 'jhi-reduction-reason-update',
    templateUrl: './reduction-reason-update.component.html'
})
export class ReductionReasonUpdateComponent implements OnInit {
    private _reductionReason: IReductionReason;
    isSaving: boolean;

    constructor(private reductionReasonService: ReductionReasonService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ reductionReason }) => {
            this.reductionReason = reductionReason;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.reductionReason.id !== undefined) {
            this.subscribeToSaveResponse(this.reductionReasonService.update(this.reductionReason));
        } else {
            this.subscribeToSaveResponse(this.reductionReasonService.create(this.reductionReason));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IReductionReason>>) {
        result.subscribe((res: HttpResponse<IReductionReason>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get reductionReason() {
        return this._reductionReason;
    }

    set reductionReason(reductionReason: IReductionReason) {
        this._reductionReason = reductionReason;
    }
}
