/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NetraTestModule } from '../../../test.module';
import { RateSchedValdDetailComponent } from 'app/entities/rate-sched-vald/rate-sched-vald-detail.component';
import { RateSchedVald } from 'app/shared/model/rate-sched-vald.model';

describe('Component Tests', () => {
    describe('RateSchedVald Management Detail Component', () => {
        let comp: RateSchedValdDetailComponent;
        let fixture: ComponentFixture<RateSchedValdDetailComponent>;
        const route = ({ data: of({ rateSchedVald: new RateSchedVald(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NetraTestModule],
                declarations: [RateSchedValdDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RateSchedValdDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RateSchedValdDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.rateSchedVald).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
