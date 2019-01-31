/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { NetraTestModule } from '../../../test.module';
import { BusinessAssociateAddressComponent } from 'app/entities/business-associate-address/business-associate-address.component';
import { BusinessAssociateAddressService } from 'app/entities/business-associate-address/business-associate-address.service';
import { BusinessAssociateAddress } from 'app/shared/model/business-associate-address.model';

describe('Component Tests', () => {
    describe('BusinessAssociateAddress Management Component', () => {
        let comp: BusinessAssociateAddressComponent;
        let fixture: ComponentFixture<BusinessAssociateAddressComponent>;
        let service: BusinessAssociateAddressService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NetraTestModule],
                declarations: [BusinessAssociateAddressComponent],
                providers: []
            })
                .overrideTemplate(BusinessAssociateAddressComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(BusinessAssociateAddressComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BusinessAssociateAddressService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new BusinessAssociateAddress(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.businessAssociateAddresses[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
