/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { NetraTestModule } from '../../../test.module';
import { RateSchedUpdateComponent } from 'app/entities/rate-sched/rate-sched-update.component';
import { RateSchedService } from 'app/entities/rate-sched/rate-sched.service';
import { RateSched } from 'app/shared/model/rate-sched.model';

describe('Component Tests', () => {
    describe('RateSched Management Update Component', () => {
        let comp: RateSchedUpdateComponent;
        let fixture: ComponentFixture<RateSchedUpdateComponent>;
        let service: RateSchedService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NetraTestModule],
                declarations: [RateSchedUpdateComponent]
            })
                .overrideTemplate(RateSchedUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RateSchedUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RateSchedService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new RateSched(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.rateSched = entity;
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
                    const entity = new RateSched();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.rateSched = entity;
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
