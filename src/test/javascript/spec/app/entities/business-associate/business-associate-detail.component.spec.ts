/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NetraTestModule } from '../../../test.module';
import { BusinessAssociateDetailComponent } from 'app/entities/business-associate/business-associate-detail.component';
import { BusinessAssociate } from 'app/shared/model/business-associate.model';

describe('Component Tests', () => {
    describe('BusinessAssociate Management Detail Component', () => {
        let comp: BusinessAssociateDetailComponent;
        let fixture: ComponentFixture<BusinessAssociateDetailComponent>;
        const route = ({ data: of({ businessAssociate: new BusinessAssociate(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NetraTestModule],
                declarations: [BusinessAssociateDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(BusinessAssociateDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(BusinessAssociateDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.businessAssociate).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
