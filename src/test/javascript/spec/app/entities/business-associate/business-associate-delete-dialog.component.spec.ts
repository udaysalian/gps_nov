/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { NetraTestModule } from '../../../test.module';
import { BusinessAssociateDeleteDialogComponent } from 'app/entities/business-associate/business-associate-delete-dialog.component';
import { BusinessAssociateService } from 'app/entities/business-associate/business-associate.service';

describe('Component Tests', () => {
    describe('BusinessAssociate Management Delete Component', () => {
        let comp: BusinessAssociateDeleteDialogComponent;
        let fixture: ComponentFixture<BusinessAssociateDeleteDialogComponent>;
        let service: BusinessAssociateService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NetraTestModule],
                declarations: [BusinessAssociateDeleteDialogComponent]
            })
                .overrideTemplate(BusinessAssociateDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(BusinessAssociateDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BusinessAssociateService);
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
