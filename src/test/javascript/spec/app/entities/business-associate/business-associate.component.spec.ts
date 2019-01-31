/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { NetraTestModule } from '../../../test.module';
import { BusinessAssociateComponent } from 'app/entities/business-associate/business-associate.component';
import { BusinessAssociateService } from 'app/entities/business-associate/business-associate.service';
import { BusinessAssociate } from 'app/shared/model/business-associate.model';

describe('Component Tests', () => {
    describe('BusinessAssociate Management Component', () => {
        let comp: BusinessAssociateComponent;
        let fixture: ComponentFixture<BusinessAssociateComponent>;
        let service: BusinessAssociateService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NetraTestModule],
                declarations: [BusinessAssociateComponent],
                providers: []
            })
                .overrideTemplate(BusinessAssociateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(BusinessAssociateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BusinessAssociateService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new BusinessAssociate(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.businessAssociates[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
