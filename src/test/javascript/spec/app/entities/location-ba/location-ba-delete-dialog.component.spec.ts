/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { NetraTestModule } from '../../../test.module';
import { LocationBADeleteDialogComponent } from 'app/entities/location-ba/location-ba-delete-dialog.component';
import { LocationBAService } from 'app/entities/location-ba/location-ba.service';

describe('Component Tests', () => {
    describe('LocationBA Management Delete Component', () => {
        let comp: LocationBADeleteDialogComponent;
        let fixture: ComponentFixture<LocationBADeleteDialogComponent>;
        let service: LocationBAService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NetraTestModule],
                declarations: [LocationBADeleteDialogComponent]
            })
                .overrideTemplate(LocationBADeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(LocationBADeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LocationBAService);
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
