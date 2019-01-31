/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { NetraTestModule } from '../../../test.module';
import { NominationPriorityComponent } from 'app/entities/nomination-priority/nomination-priority.component';
import { NominationPriorityService } from 'app/entities/nomination-priority/nomination-priority.service';
import { NominationPriority } from 'app/shared/model/nomination-priority.model';

describe('Component Tests', () => {
    describe('NominationPriority Management Component', () => {
        let comp: NominationPriorityComponent;
        let fixture: ComponentFixture<NominationPriorityComponent>;
        let service: NominationPriorityService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NetraTestModule],
                declarations: [NominationPriorityComponent],
                providers: []
            })
                .overrideTemplate(NominationPriorityComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(NominationPriorityComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(NominationPriorityService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new NominationPriority(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.nominationPriorities[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
