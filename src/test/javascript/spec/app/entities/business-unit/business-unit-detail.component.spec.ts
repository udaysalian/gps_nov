/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NetraTestModule } from '../../../test.module';
import { BusinessUnitDetailComponent } from 'app/entities/business-unit/business-unit-detail.component';
import { BusinessUnit } from 'app/shared/model/business-unit.model';

describe('Component Tests', () => {
    describe('BusinessUnit Management Detail Component', () => {
        let comp: BusinessUnitDetailComponent;
        let fixture: ComponentFixture<BusinessUnitDetailComponent>;
        const route = ({ data: of({ businessUnit: new BusinessUnit(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NetraTestModule],
                declarations: [BusinessUnitDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(BusinessUnitDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(BusinessUnitDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.businessUnit).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
