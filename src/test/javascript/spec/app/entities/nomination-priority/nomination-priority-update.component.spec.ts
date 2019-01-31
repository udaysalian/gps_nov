/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { NetraTestModule } from '../../../test.module';
import { NominationPriorityUpdateComponent } from 'app/entities/nomination-priority/nomination-priority-update.component';
import { NominationPriorityService } from 'app/entities/nomination-priority/nomination-priority.service';
import { NominationPriority } from 'app/shared/model/nomination-priority.model';

describe('Component Tests', () => {
    describe('NominationPriority Management Update Component', () => {
        let comp: NominationPriorityUpdateComponent;
        let fixture: ComponentFixture<NominationPriorityUpdateComponent>;
        let service: NominationPriorityService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NetraTestModule],
                declarations: [NominationPriorityUpdateComponent]
            })
                .overrideTemplate(NominationPriorityUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(NominationPriorityUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(NominationPriorityService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new NominationPriority(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.nominationPriority = entity;
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
                    const entity = new NominationPriority();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.nominationPriority = entity;
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
