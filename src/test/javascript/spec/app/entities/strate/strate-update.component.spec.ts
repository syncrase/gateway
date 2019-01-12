/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { GatewayTestModule } from '../../../test.module';
import { StrateUpdateComponent } from 'app/entities/strate/strate-update.component';
import { StrateService } from 'app/entities/strate/strate.service';
import { Strate } from 'app/shared/model/strate.model';

describe('Component Tests', () => {
    describe('Strate Management Update Component', () => {
        let comp: StrateUpdateComponent;
        let fixture: ComponentFixture<StrateUpdateComponent>;
        let service: StrateService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [StrateUpdateComponent]
            })
                .overrideTemplate(StrateUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(StrateUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StrateService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Strate(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.strate = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Strate();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.strate = entity;
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
