/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { NetraTestModule } from '../../../test.module';
import { ContrLocComponent } from 'app/entities/contr-loc/contr-loc.component';
import { ContrLocService } from 'app/entities/contr-loc/contr-loc.service';
import { ContrLoc } from 'app/shared/model/contr-loc.model';

describe('Component Tests', () => {
    describe('ContrLoc Management Component', () => {
        let comp: ContrLocComponent;
        let fixture: ComponentFixture<ContrLocComponent>;
        let service: ContrLocService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NetraTestModule],
                declarations: [ContrLocComponent],
                providers: []
            })
                .overrideTemplate(ContrLocComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ContrLocComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ContrLocService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ContrLoc(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.contrLocs[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
