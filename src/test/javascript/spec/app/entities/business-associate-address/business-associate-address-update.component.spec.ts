/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { NetraTestModule } from '../../../test.module';
import { BusinessAssociateAddressUpdateComponent } from 'app/entities/business-associate-address/business-associate-address-update.component';
import { BusinessAssociateAddressService } from 'app/entities/business-associate-address/business-associate-address.service';
import { BusinessAssociateAddress } from 'app/shared/model/business-associate-address.model';

describe('Component Tests', () => {
    describe('BusinessAssociateAddress Management Update Component', () => {
        let comp: BusinessAssociateAddressUpdateComponent;
        let fixture: ComponentFixture<BusinessAssociateAddressUpdateComponent>;
        let service: BusinessAssociateAddressService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NetraTestModule],
                declarations: [BusinessAssociateAddressUpdateComponent]
            })
                .overrideTemplate(BusinessAssociateAddressUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(BusinessAssociateAddressUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BusinessAssociateAddressService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new BusinessAssociateAddress(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.businessAssociateAddress = entity;
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
                    const entity = new BusinessAssociateAddress();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.businessAssociateAddress = entity;
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
