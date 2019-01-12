/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { GatewayTestModule } from '../../../test.module';
import { FloraisonUpdateComponent } from 'app/entities/floraison/floraison-update.component';
import { FloraisonService } from 'app/entities/floraison/floraison.service';
import { Floraison } from 'app/shared/model/floraison.model';

describe('Component Tests', () => {
    describe('Floraison Management Update Component', () => {
        let comp: FloraisonUpdateComponent;
        let fixture: ComponentFixture<FloraisonUpdateComponent>;
        let service: FloraisonService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [FloraisonUpdateComponent]
            })
                .overrideTemplate(FloraisonUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(FloraisonUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FloraisonService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Floraison(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.floraison = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Floraison();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.floraison = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
