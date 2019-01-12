/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GatewayTestModule } from '../../../test.module';
import { TypeRacineComponent } from 'app/entities/type-racine/type-racine.component';
import { TypeRacineService } from 'app/entities/type-racine/type-racine.service';
import { TypeRacine } from 'app/shared/model/type-racine.model';

describe('Component Tests', () => {
    describe('TypeRacine Management Component', () => {
        let comp: TypeRacineComponent;
        let fixture: ComponentFixture<TypeRacineComponent>;
        let service: TypeRacineService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [TypeRacineComponent],
                providers: []
            })
                .overrideTemplate(TypeRacineComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TypeRacineComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TypeRacineService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new TypeRacine(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.typeRacines[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
