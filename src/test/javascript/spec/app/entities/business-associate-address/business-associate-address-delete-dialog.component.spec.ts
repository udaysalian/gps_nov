/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { NetraTestModule } from '../../../test.module';
import { BusinessAssociateAddressDeleteDialogComponent } from 'app/entities/business-associate-address/business-associate-address-delete-dialog.component';
import { BusinessAssociateAddressService } from 'app/entities/business-associate-address/business-associate-address.service';

describe('Component Tests', () => {
    describe('BusinessAssociateAddress Management Delete Component', () => {
        let comp: BusinessAssociateAddressDeleteDialogComponent;
        let fixture: ComponentFixture<BusinessAssociateAddressDeleteDialogComponent>;
        let service: BusinessAssociateAddressService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NetraTestModule],
                declarations: [BusinessAssociateAddressDeleteDialogComponent]
            })
                .overrideTemplate(BusinessAssociateAddressDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(BusinessAssociateAddressDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BusinessAssociateAddressService);
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
