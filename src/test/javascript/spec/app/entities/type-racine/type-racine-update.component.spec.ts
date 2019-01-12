/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { GatewayTestModule } from '../../../test.module';
import { TypeRacineUpdateComponent } from 'app/entities/type-racine/type-racine-update.component';
import { TypeRacineService } from 'app/entities/type-racine/type-racine.service';
import { TypeRacine } from 'app/shared/model/type-racine.model';

describe('Component Tests', () => {
    describe('TypeRacine Management Update Component', () => {
        let comp: TypeRacineUpdateComponent;
        let fixture: ComponentFixture<TypeRacineUpdateComponent>;
        let service: TypeRacineService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [TypeRacineUpdateComponent]
            })
                .overrideTemplate(TypeRacineUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TypeRacineUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TypeRacineService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new TypeRacine(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.typeRacine = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new TypeRacine();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.typeRacine = entity;
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
