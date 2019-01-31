/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NetraTestModule } from '../../../test.module';
import { BusinessAssociateAddressDetailComponent } from 'app/entities/business-associate-address/business-associate-address-detail.component';
import { BusinessAssociateAddress } from 'app/shared/model/business-associate-address.model';

describe('Component Tests', () => {
    describe('BusinessAssociateAddress Management Detail Component', () => {
        let comp: BusinessAssociateAddressDetailComponent;
        let fixture: ComponentFixture<BusinessAssociateAddressDetailComponent>;
        const route = ({ data: of({ businessAssociateAddress: new BusinessAssociateAddress(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NetraTestModule],
                declarations: [BusinessAssociateAddressDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(BusinessAssociateAddressDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(BusinessAssociateAddressDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.businessAssociateAddress).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
