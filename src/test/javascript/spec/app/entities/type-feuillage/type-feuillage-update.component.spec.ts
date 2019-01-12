/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { GatewayTestModule } from '../../../test.module';
import { TypeFeuillageUpdateComponent } from 'app/entities/type-feuillage/type-feuillage-update.component';
import { TypeFeuillageService } from 'app/entities/type-feuillage/type-feuillage.service';
import { TypeFeuillage } from 'app/shared/model/type-feuillage.model';

describe('Component Tests', () => {
    describe('TypeFeuillage Management Update Component', () => {
        let comp: TypeFeuillageUpdateComponent;
        let fixture: ComponentFixture<TypeFeuillageUpdateComponent>;
        let service: TypeFeuillageService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [TypeFeuillageUpdateComponent]
            })
                .overrideTemplate(TypeFeuillageUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TypeFeuillageUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TypeFeuillageService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new TypeFeuillage(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.typeFeuillage = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new TypeFeuillage();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.typeFeuillage = entity;
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
