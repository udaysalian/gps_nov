/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NetraTestModule } from '../../../test.module';
import { ContrLocDetailComponent } from 'app/entities/contr-loc/contr-loc-detail.component';
import { ContrLoc } from 'app/shared/model/contr-loc.model';

describe('Component Tests', () => {
    describe('ContrLoc Management Detail Component', () => {
        let comp: ContrLocDetailComponent;
        let fixture: ComponentFixture<ContrLocDetailComponent>;
        const route = ({ data: of({ contrLoc: new ContrLoc(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NetraTestModule],
                declarations: [ContrLocDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ContrLocDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ContrLocDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.contrLoc).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
