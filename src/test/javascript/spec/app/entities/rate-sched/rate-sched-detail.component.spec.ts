/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NetraTestModule } from '../../../test.module';
import { RateSchedDetailComponent } from 'app/entities/rate-sched/rate-sched-detail.component';
import { RateSched } from 'app/shared/model/rate-sched.model';

describe('Component Tests', () => {
    describe('RateSched Management Detail Component', () => {
        let comp: RateSchedDetailComponent;
        let fixture: ComponentFixture<RateSchedDetailComponent>;
        const route = ({ data: of({ rateSched: new RateSched(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NetraTestModule],
                declarations: [RateSchedDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RateSchedDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RateSchedDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.rateSched).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
