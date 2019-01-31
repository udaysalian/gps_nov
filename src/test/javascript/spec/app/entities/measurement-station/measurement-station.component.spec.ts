/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { NetraTestModule } from '../../../test.module';
import { MeasurementStationComponent } from 'app/entities/measurement-station/measurement-station.component';
import { MeasurementStationService } from 'app/entities/measurement-station/measurement-station.service';
import { MeasurementStation } from 'app/shared/model/measurement-station.model';

describe('Component Tests', () => {
    describe('MeasurementStation Management Component', () => {
        let comp: MeasurementStationComponent;
        let fixture: ComponentFixture<MeasurementStationComponent>;
        let service: MeasurementStationService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NetraTestModule],
                declarations: [MeasurementStationComponent],
                providers: []
            })
                .overrideTemplate(MeasurementStationComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(MeasurementStationComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MeasurementStationService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new MeasurementStation(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.measurementStations[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
