/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { NetraTestModule } from '../../../test.module';
import { MeasurementStationDeleteDialogComponent } from 'app/entities/measurement-station/measurement-station-delete-dialog.component';
import { MeasurementStationService } from 'app/entities/measurement-station/measurement-station.service';

describe('Component Tests', () => {
    describe('MeasurementStation Management Delete Component', () => {
        let comp: MeasurementStationDeleteDialogComponent;
        let fixture: ComponentFixture<MeasurementStationDeleteDialogComponent>;
        let service: MeasurementStationService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NetraTestModule],
                declarations: [MeasurementStationDeleteDialogComponent]
            })
                .overrideTemplate(MeasurementStationDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(MeasurementStationDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MeasurementStationService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
