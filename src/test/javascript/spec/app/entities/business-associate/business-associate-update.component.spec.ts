/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { NetraTestModule } from '../../../test.module';
import { BusinessAssociateUpdateComponent } from 'app/entities/business-associate/business-associate-update.component';
import { BusinessAssociateService } from 'app/entities/business-associate/business-associate.service';
import { BusinessAssociate } from 'app/shared/model/business-associate.model';

describe('Component Tests', () => {
    describe('BusinessAssociate Management Update Component', () => {
        let comp: BusinessAssociateUpdateComponent;
        let fixture: ComponentFixture<BusinessAssociateUpdateComponent>;
        let service: BusinessAssociateService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NetraTestModule],
                declarations: [BusinessAssociateUpdateComponent]
            })
                .overrideTemplate(BusinessAssociateUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(BusinessAssociateUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BusinessAssociateService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new BusinessAssociate(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.businessAssociate = entity;
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
                    const entity = new BusinessAssociate();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.businessAssociate = entity;
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
