/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { GatewayTestModule } from '../../../test.module';
import { RecolteUpdateComponent } from 'app/entities/recolte/recolte-update.component';
import { RecolteService } from 'app/entities/recolte/recolte.service';
import { Recolte } from 'app/shared/model/recolte.model';

describe('Component Tests', () => {
    describe('Recolte Management Update Component', () => {
        let comp: RecolteUpdateComponent;
        let fixture: ComponentFixture<RecolteUpdateComponent>;
        let service: RecolteService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [RecolteUpdateComponent]
            })
                .overrideTemplate(RecolteUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RecolteUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RecolteService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Recolte(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.recolte = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Recolte();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.recolte = entity;
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
