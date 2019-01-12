/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { GatewayTestModule } from '../../../test.module';
import { TypeTerreUpdateComponent } from 'app/entities/type-terre/type-terre-update.component';
import { TypeTerreService } from 'app/entities/type-terre/type-terre.service';
import { TypeTerre } from 'app/shared/model/type-terre.model';

describe('Component Tests', () => {
    describe('TypeTerre Management Update Component', () => {
        let comp: TypeTerreUpdateComponent;
        let fixture: ComponentFixture<TypeTerreUpdateComponent>;
        let service: TypeTerreService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [TypeTerreUpdateComponent]
            })
                .overrideTemplate(TypeTerreUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TypeTerreUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TypeTerreService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new TypeTerre(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.typeTerre = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new TypeTerre();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.typeTerre = entity;
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
