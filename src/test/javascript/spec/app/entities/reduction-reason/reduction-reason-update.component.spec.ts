/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { NetraTestModule } from '../../../test.module';
import { ReductionReasonUpdateComponent } from 'app/entities/reduction-reason/reduction-reason-update.component';
import { ReductionReasonService } from 'app/entities/reduction-reason/reduction-reason.service';
import { ReductionReason } from 'app/shared/model/reduction-reason.model';

describe('Component Tests', () => {
    describe('ReductionReason Management Update Component', () => {
        let comp: ReductionReasonUpdateComponent;
        let fixture: ComponentFixture<ReductionReasonUpdateComponent>;
        let service: ReductionReasonService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NetraTestModule],
                declarations: [ReductionReasonUpdateComponent]
            })
                .overrideTemplate(ReductionReasonUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ReductionReasonUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ReductionReasonService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ReductionReason(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.reductionReason = entity;
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
                    const entity = new ReductionReason();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.reductionReason = entity;
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
