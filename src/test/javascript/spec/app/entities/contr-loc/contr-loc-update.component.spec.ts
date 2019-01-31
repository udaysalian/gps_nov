/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { NetraTestModule } from '../../../test.module';
import { ContrLocUpdateComponent } from 'app/entities/contr-loc/contr-loc-update.component';
import { ContrLocService } from 'app/entities/contr-loc/contr-loc.service';
import { ContrLoc } from 'app/shared/model/contr-loc.model';

describe('Component Tests', () => {
    describe('ContrLoc Management Update Component', () => {
        let comp: ContrLocUpdateComponent;
        let fixture: ComponentFixture<ContrLocUpdateComponent>;
        let service: ContrLocService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NetraTestModule],
                declarations: [ContrLocUpdateComponent]
            })
                .overrideTemplate(ContrLocUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ContrLocUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ContrLocService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ContrLoc(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.contrLoc = entity;
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
                    const entity = new ContrLoc();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.contrLoc = entity;
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
