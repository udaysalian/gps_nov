/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NetraTestModule } from '../../../test.module';
import { ReductionReasonDetailComponent } from 'app/entities/reduction-reason/reduction-reason-detail.component';
import { ReductionReason } from 'app/shared/model/reduction-reason.model';

describe('Component Tests', () => {
    describe('ReductionReason Management Detail Component', () => {
        let comp: ReductionReasonDetailComponent;
        let fixture: ComponentFixture<ReductionReasonDetailComponent>;
        const route = ({ data: of({ reductionReason: new ReductionReason(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NetraTestModule],
                declarations: [ReductionReasonDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ReductionReasonDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ReductionReasonDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.reductionReason).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
