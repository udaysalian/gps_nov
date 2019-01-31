/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NetraTestModule } from '../../../test.module';
import { NominationDetailComponent } from 'app/entities/nomination/nomination-detail.component';
import { Nomination } from 'app/shared/model/nomination.model';

describe('Component Tests', () => {
    describe('Nomination Management Detail Component', () => {
        let comp: NominationDetailComponent;
        let fixture: ComponentFixture<NominationDetailComponent>;
        const route = ({ data: of({ nomination: new Nomination(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NetraTestModule],
                declarations: [NominationDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(NominationDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(NominationDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.nomination).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
