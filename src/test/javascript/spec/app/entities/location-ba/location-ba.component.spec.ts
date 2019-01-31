/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { NetraTestModule } from '../../../test.module';
import { LocationBAComponent } from 'app/entities/location-ba/location-ba.component';
import { LocationBAService } from 'app/entities/location-ba/location-ba.service';
import { LocationBA } from 'app/shared/model/location-ba.model';

describe('Component Tests', () => {
    describe('LocationBA Management Component', () => {
        let comp: LocationBAComponent;
        let fixture: ComponentFixture<LocationBAComponent>;
        let service: LocationBAService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NetraTestModule],
                declarations: [LocationBAComponent],
                providers: []
            })
                .overrideTemplate(LocationBAComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(LocationBAComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LocationBAService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new LocationBA(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.locationBAS[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
