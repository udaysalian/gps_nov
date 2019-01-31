import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBusinessAssociate } from 'app/shared/model/business-associate.model';
import { BusinessAssociateService } from './business-associate.service';

@Component({
    selector: 'jhi-business-associate-delete-dialog',
    templateUrl: './business-associate-delete-dialog.component.html'
})
export class BusinessAssociateDeleteDialogComponent {
    businessAssociate: IBusinessAssociate;

    constructor(
        private businessAssociateService: BusinessAssociateService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.businessAssociateService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'businessAssociateListModification',
                content: 'Deleted an businessAssociate'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-business-associate-delete-popup',
    template: ''
})
export class BusinessAssociateDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ businessAssociate }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(BusinessAssociateDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.businessAssociate = businessAssociate;
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
