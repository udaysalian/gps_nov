import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILocationBA } from 'app/shared/model/location-ba.model';
import { LocationBAService } from './location-ba.service';

@Component({
    selector: 'jhi-location-ba-delete-dialog',
    templateUrl: './location-ba-delete-dialog.component.html'
})
export class LocationBADeleteDialogComponent {
    locationBA: ILocationBA;

    constructor(private locationBAService: LocationBAService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.locationBAService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'locationBAListModification',
                content: 'Deleted an locationBA'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-location-ba-delete-popup',
    template: ''
})
export class LocationBADeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ locationBA }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(LocationBADeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.locationBA = locationBA;
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
