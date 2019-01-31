/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { NetraTestModule } from '../../../test.module';
import { RateSchedValdUpdateComponent } from 'app/entities/rate-sched-vald/rate-sched-vald-update.component';
import { RateSchedValdService } from 'app/entities/rate-sched-vald/rate-sched-vald.service';
import { RateSchedVald } from 'app/shared/model/rate-sched-vald.model';

describe('Component Tests', () => {
    describe('RateSchedVald Management Update Component', () => {
        let comp: RateSchedValdUpdateComponent;
        let fixture: ComponentFixture<RateSchedValdUpdateComponent>;
        let service: RateSchedValdService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NetraTestModule],
                declarations: [RateSchedValdUpdateComponent]
            })
                .overrideTemplate(RateSchedValdUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RateSchedValdUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RateSchedValdService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new RateSchedVald(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.rateSchedVald = entity;
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
                    const entity = new RateSchedVald();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.rateSchedVald = entity;
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
