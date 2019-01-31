/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NetraTestModule } from '../../../test.module';
import { LocationBADetailComponent } from 'app/entities/location-ba/location-ba-detail.component';
import { LocationBA } from 'app/shared/model/location-ba.model';

describe('Component Tests', () => {
    describe('LocationBA Management Detail Component', () => {
        let comp: LocationBADetailComponent;
        let fixture: ComponentFixture<LocationBADetailComponent>;
        const route = ({ data: of({ locationBA: new LocationBA(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NetraTestModule],
                declarations: [LocationBADetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(LocationBADetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(LocationBADetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.locationBA).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
