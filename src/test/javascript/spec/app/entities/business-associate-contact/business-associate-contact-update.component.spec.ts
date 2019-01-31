/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { NetraTestModule } from '../../../test.module';
import { BusinessAssociateContactUpdateComponent } from 'app/entities/business-associate-contact/business-associate-contact-update.component';
import { BusinessAssociateContactService } from 'app/entities/business-associate-contact/business-associate-contact.service';
import { BusinessAssociateContact } from 'app/shared/model/business-associate-contact.model';

describe('Component Tests', () => {
    describe('BusinessAssociateContact Management Update Component', () => {
        let comp: BusinessAssociateContactUpdateComponent;
        let fixture: ComponentFixture<BusinessAssociateContactUpdateComponent>;
        let service: BusinessAssociateContactService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NetraTestModule],
                declarations: [BusinessAssociateContactUpdateComponent]
            })
                .overrideTemplate(BusinessAssociateContactUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(BusinessAssociateContactUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BusinessAssociateContactService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new BusinessAssociateContact(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.businessAssociateContact = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new BusinessAssociateContact();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.businessAssociateContact = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
