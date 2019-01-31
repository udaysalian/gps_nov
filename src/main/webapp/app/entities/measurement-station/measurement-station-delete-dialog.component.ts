import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMeasurementStation } from 'app/shared/model/measurement-station.model';
import { MeasurementStationService } from './measurement-station.service';

@Component({
    selector: 'jhi-measurement-station-delete-dialog',
    templateUrl: './measurement-station-delete-dialog.component.html'
})
export class MeasurementStationDeleteDialogComponent {
    measurementStation: IMeasurementStation;

    constructor(
        private measurementStationService: MeasurementStationService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.measurementStationService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'measurementStationListModification',
                content: 'Deleted an measurementStation'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-measurement-station-delete-popup',
    template: ''
})
export class MeasurementStationDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ measurementStation }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(MeasurementStationDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.measurementStation = measurementStation;
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
