/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { NetraTestModule } from '../../../test.module';
import { RateSchedValdDeleteDialogComponent } from 'app/entities/rate-sched-vald/rate-sched-vald-delete-dialog.component';
import { RateSchedValdService } from 'app/entities/rate-sched-vald/rate-sched-vald.service';

describe('Component Tests', () => {
    describe('RateSchedVald Management Delete Component', () => {
        let comp: RateSchedValdDeleteDialogComponent;
        let fixture: ComponentFixture<RateSchedValdDeleteDialogComponent>;
        let service: RateSchedValdService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NetraTestModule],
                declarations: [RateSchedValdDeleteDialogComponent]
            })
                .overrideTemplate(RateSchedValdDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RateSchedValdDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RateSchedValdService);
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
