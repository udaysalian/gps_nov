/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { NetraTestModule } from '../../../test.module';
import { NominationComponent } from 'app/entities/nomination/nomination.component';
import { NominationService } from 'app/entities/nomination/netraNomination.service';
import { Nomination } from 'app/shared/model/nomination.model';

describe('Component Tests', () => {
    describe('Nomination Management Component', () => {
        let comp: NominationComponent;
        let fixture: ComponentFixture<NominationComponent>;
        let service: NominationService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NetraTestModule],
                declarations: [NominationComponent],
                providers: []
            })
                .overrideTemplate(NominationComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(NominationComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(NominationService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Nomination(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.nominations[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
