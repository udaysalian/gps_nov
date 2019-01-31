import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INominationPriority } from 'app/shared/model/nomination-priority.model';
import { NominationPriorityService } from './nomination-priority.service';

@Component({
    selector: 'jhi-nomination-priority-delete-dialog',
    templateUrl: './nomination-priority-delete-dialog.component.html'
})
export class NominationPriorityDeleteDialogComponent {
    nominationPriority: INominationPriority;

    constructor(
        private nominationPriorityService: NominationPriorityService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.nominationPriorityService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'nominationPriorityListModification',
                content: 'Deleted an nominationPriority'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-nomination-priority-delete-popup',
    template: ''
})
export class NominationPriorityDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ nominationPriority }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(NominationPriorityDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.nominationPriority = nominationPriority;
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
