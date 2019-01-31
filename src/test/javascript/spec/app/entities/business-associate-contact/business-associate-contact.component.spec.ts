/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { NetraTestModule } from '../../../test.module';
import { BusinessAssociateContactComponent } from 'app/entities/business-associate-contact/business-associate-contact.component';
import { BusinessAssociateContactService } from 'app/entities/business-associate-contact/business-associate-contact.service';
import { BusinessAssociateContact } from 'app/shared/model/business-associate-contact.model';

describe('Component Tests', () => {
    describe('BusinessAssociateContact Management Component', () => {
        let comp: BusinessAssociateContactComponent;
        let fixture: ComponentFixture<BusinessAssociateContactComponent>;
        let service: BusinessAssociateContactService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NetraTestModule],
                declarations: [BusinessAssociateContactComponent],
                providers: []
            })
                .overrideTemplate(BusinessAssociateContactComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(BusinessAssociateContactComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BusinessAssociateContactService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new BusinessAssociateContact(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.businessAssociateContacts[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
