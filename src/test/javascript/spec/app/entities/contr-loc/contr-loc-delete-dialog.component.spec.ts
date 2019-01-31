/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { NetraTestModule } from '../../../test.module';
import { ContrLocDeleteDialogComponent } from 'app/entities/contr-loc/contr-loc-delete-dialog.component';
import { ContrLocService } from 'app/entities/contr-loc/contr-loc.service';

describe('Component Tests', () => {
    describe('ContrLoc Management Delete Component', () => {
        let comp: ContrLocDeleteDialogComponent;
        let fixture: ComponentFixture<ContrLocDeleteDialogComponent>;
        let service: ContrLocService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NetraTestModule],
                declarations: [ContrLocDeleteDialogComponent]
            })
                .overrideTemplate(ContrLocDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ContrLocDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ContrLocService);
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
