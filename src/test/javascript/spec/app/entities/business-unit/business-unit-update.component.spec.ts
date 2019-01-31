/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { NetraTestModule } from '../../../test.module';
import { BusinessUnitUpdateComponent } from 'app/entities/business-unit/business-unit-update.component';
import { BusinessUnitService } from 'app/entities/business-unit/business-unit.service';
import { BusinessUnit } from 'app/shared/model/business-unit.model';

describe('Component Tests', () => {
    describe('BusinessUnit Management Update Component', () => {
        let comp: BusinessUnitUpdateComponent;
        let fixture: ComponentFixture<BusinessUnitUpdateComponent>;
        let service: BusinessUnitService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NetraTestModule],
                declarations: [BusinessUnitUpdateComponent]
            })
                .overrideTemplate(BusinessUnitUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(BusinessUnitUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BusinessUnitService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new BusinessUnit(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.businessUnit = entity;
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
                    const entity = new BusinessUnit();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.businessUnit = entity;
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
