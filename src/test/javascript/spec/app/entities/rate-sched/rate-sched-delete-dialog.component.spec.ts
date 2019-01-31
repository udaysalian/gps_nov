/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { NetraTestModule } from '../../../test.module';
import { RateSchedDeleteDialogComponent } from 'app/entities/rate-sched/rate-sched-delete-dialog.component';
import { RateSchedService } from 'app/entities/rate-sched/rate-sched.service';

describe('Component Tests', () => {
    describe('RateSched Management Delete Component', () => {
        let comp: RateSchedDeleteDialogComponent;
        let fixture: ComponentFixture<RateSchedDeleteDialogComponent>;
        let service: RateSchedService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NetraTestModule],
                declarations: [RateSchedDeleteDialogComponent]
            })
                .overrideTemplate(RateSchedDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RateSchedDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RateSchedService);
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
