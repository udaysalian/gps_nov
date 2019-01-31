/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { NetraTestModule } from '../../../test.module';
import { RateSchedComponent } from 'app/entities/rate-sched/rate-sched.component';
import { RateSchedService } from 'app/entities/rate-sched/rate-sched.service';
import { RateSched } from 'app/shared/model/rate-sched.model';

describe('Component Tests', () => {
    describe('RateSched Management Component', () => {
        let comp: RateSchedComponent;
        let fixture: ComponentFixture<RateSchedComponent>;
        let service: RateSchedService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NetraTestModule],
                declarations: [RateSchedComponent],
                providers: []
            })
                .overrideTemplate(RateSchedComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RateSchedComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RateSchedService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new RateSched(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.rateScheds[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
