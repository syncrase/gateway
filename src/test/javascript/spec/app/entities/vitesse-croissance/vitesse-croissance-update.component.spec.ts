/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { GatewayTestModule } from '../../../test.module';
import { VitesseCroissanceUpdateComponent } from 'app/entities/vitesse-croissance/vitesse-croissance-update.component';
import { VitesseCroissanceService } from 'app/entities/vitesse-croissance/vitesse-croissance.service';
import { VitesseCroissance } from 'app/shared/model/vitesse-croissance.model';

describe('Component Tests', () => {
    describe('VitesseCroissance Management Update Component', () => {
        let comp: VitesseCroissanceUpdateComponent;
        let fixture: ComponentFixture<VitesseCroissanceUpdateComponent>;
        let service: VitesseCroissanceService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [VitesseCroissanceUpdateComponent]
            })
                .overrideTemplate(VitesseCroissanceUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(VitesseCroissanceUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VitesseCroissanceService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new VitesseCroissance(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.vitesseCroissance = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new VitesseCroissance();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.vitesseCroissance = entity;
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
