/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { GatewayTestModule } from '../../../test.module';
import { RichesseSolUpdateComponent } from 'app/entities/richesse-sol/richesse-sol-update.component';
import { RichesseSolService } from 'app/entities/richesse-sol/richesse-sol.service';
import { RichesseSol } from 'app/shared/model/richesse-sol.model';

describe('Component Tests', () => {
    describe('RichesseSol Management Update Component', () => {
        let comp: RichesseSolUpdateComponent;
        let fixture: ComponentFixture<RichesseSolUpdateComponent>;
        let service: RichesseSolService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [RichesseSolUpdateComponent]
            })
                .overrideTemplate(RichesseSolUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RichesseSolUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RichesseSolService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new RichesseSol(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.richesseSol = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new RichesseSol();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.richesseSol = entity;
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
