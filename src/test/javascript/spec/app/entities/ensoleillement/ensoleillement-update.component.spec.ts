/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { GatewayTestModule } from '../../../test.module';
import { EnsoleillementUpdateComponent } from 'app/entities/ensoleillement/ensoleillement-update.component';
import { EnsoleillementService } from 'app/entities/ensoleillement/ensoleillement.service';
import { Ensoleillement } from 'app/shared/model/ensoleillement.model';

describe('Component Tests', () => {
    describe('Ensoleillement Management Update Component', () => {
        let comp: EnsoleillementUpdateComponent;
        let fixture: ComponentFixture<EnsoleillementUpdateComponent>;
        let service: EnsoleillementService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [EnsoleillementUpdateComponent]
            })
                .overrideTemplate(EnsoleillementUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EnsoleillementUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EnsoleillementService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Ensoleillement(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.ensoleillement = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Ensoleillement();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.ensoleillement = entity;
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
