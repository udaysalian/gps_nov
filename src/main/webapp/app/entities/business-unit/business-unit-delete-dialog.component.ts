import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBusinessUnit } from 'app/shared/model/business-unit.model';
import { BusinessUnitService } from './business-unit.service';

@Component({
    selector: 'jhi-business-unit-delete-dialog',
    templateUrl: './business-unit-delete-dialog.component.html'
})
export class BusinessUnitDeleteDialogComponent {
    businessUnit: IBusinessUnit;

    constructor(
        private businessUnitService: BusinessUnitService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.businessUnitService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'businessUnitListModification',
                content: 'Deleted an businessUnit'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-business-unit-delete-popup',
    template: ''
})
export class BusinessUnitDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ businessUnit }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(BusinessUnitDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.businessUnit = businessUnit;
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
