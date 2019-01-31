/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { NetraTestModule } from '../../../test.module';
import { ReductionReasonComponent } from 'app/entities/reduction-reason/reduction-reason.component';
import { ReductionReasonService } from 'app/entities/reduction-reason/reduction-reason.service';
import { ReductionReason } from 'app/shared/model/reduction-reason.model';

describe('Component Tests', () => {
    describe('ReductionReason Management Component', () => {
        let comp: ReductionReasonComponent;
        let fixture: ComponentFixture<ReductionReasonComponent>;
        let service: ReductionReasonService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NetraTestModule],
                declarations: [ReductionReasonComponent],
                providers: []
            })
                .overrideTemplate(ReductionReasonComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ReductionReasonComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ReductionReasonService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ReductionReason(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.reductionReasons[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
