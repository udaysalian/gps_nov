/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { NetraTestModule } from '../../../test.module';
import { BusinessUnitComponent } from 'app/entities/business-unit/business-unit.component';
import { BusinessUnitService } from 'app/entities/business-unit/business-unit.service';
import { BusinessUnit } from 'app/shared/model/business-unit.model';

describe('Component Tests', () => {
    describe('BusinessUnit Management Component', () => {
        let comp: BusinessUnitComponent;
        let fixture: ComponentFixture<BusinessUnitComponent>;
        let service: BusinessUnitService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NetraTestModule],
                declarations: [BusinessUnitComponent],
                providers: []
            })
                .overrideTemplate(BusinessUnitComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(BusinessUnitComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BusinessUnitService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new BusinessUnit(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.businessUnits[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
