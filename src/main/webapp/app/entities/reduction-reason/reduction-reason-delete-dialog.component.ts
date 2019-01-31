import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IReductionReason } from 'app/shared/model/reduction-reason.model';
import { ReductionReasonService } from './reduction-reason.service';

@Component({
    selector: 'jhi-reduction-reason-delete-dialog',
    templateUrl: './reduction-reason-delete-dialog.component.html'
})
export class ReductionReasonDeleteDialogComponent {
    reductionReason: IReductionReason;

    constructor(
        private reductionReasonService: ReductionReasonService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.reductionReasonService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'reductionReasonListModification',
                content: 'Deleted an reductionReason'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-reduction-reason-delete-popup',
    template: ''
})
export class ReductionReasonDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ reductionReason }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ReductionReasonDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.reductionReason = reductionReason;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
