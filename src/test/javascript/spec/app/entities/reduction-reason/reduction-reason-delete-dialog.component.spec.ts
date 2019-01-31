/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { NetraTestModule } from '../../../test.module';
import { ReductionReasonDeleteDialogComponent } from 'app/entities/reduction-reason/reduction-reason-delete-dialog.component';
import { ReductionReasonService } from 'app/entities/reduction-reason/reduction-reason.service';

describe('Component Tests', () => {
    describe('ReductionReason Management Delete Component', () => {
        let comp: ReductionReasonDeleteDialogComponent;
        let fixture: ComponentFixture<ReductionReasonDeleteDialogComponent>;
        let service: ReductionReasonService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NetraTestModule],
                declarations: [ReductionReasonDeleteDialogComponent]
            })
                .overrideTemplate(ReductionReasonDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ReductionReasonDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ReductionReasonService);
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
