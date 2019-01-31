/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { NetraTestModule } from '../../../test.module';
import { MeasurementStationUpdateComponent } from 'app/entities/measurement-station/measurement-station-update.component';
import { MeasurementStationService } from 'app/entities/measurement-station/measurement-station.service';
import { MeasurementStation } from 'app/shared/model/measurement-station.model';

describe('Component Tests', () => {
    describe('MeasurementStation Management Update Component', () => {
        let comp: MeasurementStationUpdateComponent;
        let fixture: ComponentFixture<MeasurementStationUpdateComponent>;
        let service: MeasurementStationService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NetraTestModule],
                declarations: [MeasurementStationUpdateComponent]
            })
                .overrideTemplate(MeasurementStationUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(MeasurementStationUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MeasurementStationService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new MeasurementStation(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.measurementStation = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new MeasurementStation();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.measurementStation = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
