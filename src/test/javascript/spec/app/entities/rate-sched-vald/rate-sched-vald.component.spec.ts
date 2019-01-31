/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { NetraTestModule } from '../../../test.module';
import { RateSchedValdComponent } from 'app/entities/rate-sched-vald/rate-sched-vald.component';
import { RateSchedValdService } from 'app/entities/rate-sched-vald/rate-sched-vald.service';
import { RateSchedVald } from 'app/shared/model/rate-sched-vald.model';

describe('Component Tests', () => {
    describe('RateSchedVald Management Component', () => {
        let comp: RateSchedValdComponent;
        let fixture: ComponentFixture<RateSchedValdComponent>;
        let service: RateSchedValdService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NetraTestModule],
                declarations: [RateSchedValdComponent],
                providers: []
            })
                .overrideTemplate(RateSchedValdComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RateSchedValdComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RateSchedValdService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new RateSchedVald(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.rateSchedValds[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
