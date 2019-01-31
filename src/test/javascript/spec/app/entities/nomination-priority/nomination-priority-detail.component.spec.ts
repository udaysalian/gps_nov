/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NetraTestModule } from '../../../test.module';
import { NominationPriorityDetailComponent } from 'app/entities/nomination-priority/nomination-priority-detail.component';
import { NominationPriority } from 'app/shared/model/nomination-priority.model';

describe('Component Tests', () => {
    describe('NominationPriority Management Detail Component', () => {
        let comp: NominationPriorityDetailComponent;
        let fixture: ComponentFixture<NominationPriorityDetailComponent>;
        const route = ({ data: of({ nominationPriority: new NominationPriority(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NetraTestModule],
                declarations: [NominationPriorityDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(NominationPriorityDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(NominationPriorityDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.nominationPriority).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
