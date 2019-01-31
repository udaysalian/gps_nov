import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBusinessAssociateAddress } from 'app/shared/model/business-associate-address.model';
import { BusinessAssociateAddressService } from './business-associate-address.service';

@Component({
    selector: 'jhi-business-associate-address-delete-dialog',
    templateUrl: './business-associate-address-delete-dialog.component.html'
})
export class BusinessAssociateAddressDeleteDialogComponent {
    businessAssociateAddress: IBusinessAssociateAddress;

    constructor(
        private businessAssociateAddressService: BusinessAssociateAddressService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.businessAssociateAddressService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'businessAssociateAddressListModification',
                content: 'Deleted an businessAssociateAddress'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-business-associate-address-delete-popup',
    template: ''
})
export class BusinessAssociateAddressDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ businessAssociateAddress }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(BusinessAssociateAddressDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.businessAssociateAddress = businessAssociateAddress;
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
