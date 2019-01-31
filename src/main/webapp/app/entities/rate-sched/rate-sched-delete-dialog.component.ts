import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRateSched } from 'app/shared/model/rate-sched.model';
import { RateSchedService } from './rate-sched.service';

@Component({
    selector: 'jhi-rate-sched-delete-dialog',
    templateUrl: './rate-sched-delete-dialog.component.html'
})
export class RateSchedDeleteDialogComponent {
    rateSched: IRateSched;

    constructor(private rateSchedService: RateSchedService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.rateSchedService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'rateSchedListModification',
                content: 'Deleted an rateSched'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-rate-sched-delete-popup',
    template: ''
})
export class RateSchedDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ rateSched }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RateSchedDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.rateSched = rateSched;
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
