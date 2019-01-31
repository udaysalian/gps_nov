/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NetraTestModule } from '../../../test.module';
import { BusinessAssociateContactDetailComponent } from 'app/entities/business-associate-contact/business-associate-contact-detail.component';
import { BusinessAssociateContact } from 'app/shared/model/business-associate-contact.model';

describe('Component Tests', () => {
    describe('BusinessAssociateContact Management Detail Component', () => {
        let comp: BusinessAssociateContactDetailComponent;
        let fixture: ComponentFixture<BusinessAssociateContactDetailComponent>;
        const route = ({ data: of({ businessAssociateContact: new BusinessAssociateContact(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NetraTestModule],
                declarations: [BusinessAssociateContactDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(BusinessAssociateContactDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(BusinessAssociateContactDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.businessAssociateContact).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
