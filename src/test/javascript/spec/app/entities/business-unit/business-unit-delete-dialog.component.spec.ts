/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { NetraTestModule } from '../../../test.module';
import { BusinessUnitDeleteDialogComponent } from 'app/entities/business-unit/business-unit-delete-dialog.component';
import { BusinessUnitService } from 'app/entities/business-unit/business-unit.service';

describe('Component Tests', () => {
    describe('BusinessUnit Management Delete Component', () => {
        let comp: BusinessUnitDeleteDialogComponent;
        let fixture: ComponentFixture<BusinessUnitDeleteDialogComponent>;
        let service: BusinessUnitService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NetraTestModule],
                declarations: [BusinessUnitDeleteDialogComponent]
            })
                .overrideTemplate(BusinessUnitDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(BusinessUnitDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BusinessUnitService);
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
