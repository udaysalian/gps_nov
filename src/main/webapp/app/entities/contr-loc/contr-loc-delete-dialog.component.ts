import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IContrLoc } from 'app/shared/model/contr-loc.model';
import { ContrLocService } from './contr-loc.service';

@Component({
    selector: 'jhi-contr-loc-delete-dialog',
    templateUrl: './contr-loc-delete-dialog.component.html'
})
export class ContrLocDeleteDialogComponent {
    contrLoc: IContrLoc;

    constructor(private contrLocService: ContrLocService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.contrLocService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'contrLocListModification',
                content: 'Deleted an contrLoc'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-contr-loc-delete-popup',
    template: ''
})
export class ContrLocDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ contrLoc }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ContrLocDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.contrLoc = contrLoc;
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
