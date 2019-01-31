import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRateSchedVald } from 'app/shared/model/rate-sched-vald.model';
import { RateSchedValdService } from './rate-sched-vald.service';

@Component({
    selector: 'jhi-rate-sched-vald-delete-dialog',
    templateUrl: './rate-sched-vald-delete-dialog.component.html'
})
export class RateSchedValdDeleteDialogComponent {
    rateSchedVald: IRateSchedVald;

    constructor(
        private rateSchedValdService: RateSchedValdService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.rateSchedValdService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'rateSchedValdListModification',
                content: 'Deleted an rateSchedVald'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-rate-sched-vald-delete-popup',
    template: ''
})
export class RateSchedValdDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ rateSchedVald }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RateSchedValdDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.rateSchedVald = rateSchedVald;
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
