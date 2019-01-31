/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NetraTestModule } from '../../../test.module';
import { MeasurementStationDetailComponent } from 'app/entities/measurement-station/measurement-station-detail.component';
import { MeasurementStation } from 'app/shared/model/measurement-station.model';

describe('Component Tests', () => {
    describe('MeasurementStation Management Detail Component', () => {
        let comp: MeasurementStationDetailComponent;
        let fixture: ComponentFixture<MeasurementStationDetailComponent>;
        const route = ({ data: of({ measurementStation: new MeasurementStation(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NetraTestModule],
                declarations: [MeasurementStationDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(MeasurementStationDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(MeasurementStationDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.measurementStation).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
