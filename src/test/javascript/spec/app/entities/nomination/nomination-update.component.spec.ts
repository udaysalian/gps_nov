/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { NetraTestModule } from '../../../test.module';
import { NominationUpdateComponent } from 'app/entities/nomination/nomination-update.component';
import { NominationService } from 'app/entities/nomination/netraNomination.service';
import { Nomination } from 'app/shared/model/nomination.model';

describe('Component Tests', () => {
    describe('Nomination Management Update Component', () => {
        let comp: NominationUpdateComponent;
        let fixture: ComponentFixture<NominationUpdateComponent>;
        let service: NominationService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NetraTestModule],
                declarations: [NominationUpdateComponent]
            })
                .overrideTemplate(NominationUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(NominationUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(NominationService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Nomination(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.nomination = entity;
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
                    const entity = new Nomination();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.nomination = entity;
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
