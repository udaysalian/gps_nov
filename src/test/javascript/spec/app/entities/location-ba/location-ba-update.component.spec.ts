/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { NetraTestModule } from '../../../test.module';
import { LocationBAUpdateComponent } from 'app/entities/location-ba/location-ba-update.component';
import { LocationBAService } from 'app/entities/location-ba/location-ba.service';
import { LocationBA } from 'app/shared/model/location-ba.model';

describe('Component Tests', () => {
    describe('LocationBA Management Update Component', () => {
        let comp: LocationBAUpdateComponent;
        let fixture: ComponentFixture<LocationBAUpdateComponent>;
        let service: LocationBAService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NetraTestModule],
                declarations: [LocationBAUpdateComponent]
            })
                .overrideTemplate(LocationBAUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(LocationBAUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LocationBAService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new LocationBA(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.locationBA = entity;
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
                    const entity = new LocationBA();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.locationBA = entity;
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
