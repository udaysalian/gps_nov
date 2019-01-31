import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBusinessAssociateContact } from 'app/shared/model/business-associate-contact.model';
import { BusinessAssociateContactService } from './business-associate-contact.service';

@Component({
    selector: 'jhi-business-associate-contact-delete-dialog',
    templateUrl: './business-associate-contact-delete-dialog.component.html'
})
export class BusinessAssociateContactDeleteDialogComponent {
    businessAssociateContact: IBusinessAssociateContact;

    constructor(
        private businessAssociateContactService: BusinessAssociateContactService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.businessAssociateContactService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'businessAssociateContactListModification',
                content: 'Deleted an businessAssociateContact'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-business-associate-contact-delete-popup',
    template: ''
})
export class BusinessAssociateContactDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ businessAssociateContact }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(BusinessAssociateContactDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.businessAssociateContact = businessAssociateContact;
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
