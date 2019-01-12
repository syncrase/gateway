/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GatewayTestModule } from '../../../test.module';
import { TypeFeuillageComponent } from 'app/entities/type-feuillage/type-feuillage.component';
import { TypeFeuillageService } from 'app/entities/type-feuillage/type-feuillage.service';
import { TypeFeuillage } from 'app/shared/model/type-feuillage.model';

describe('Component Tests', () => {
    describe('TypeFeuillage Management Component', () => {
        let comp: TypeFeuillageComponent;
        let fixture: ComponentFixture<TypeFeuillageComponent>;
        let service: TypeFeuillageService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [TypeFeuillageComponent],
                providers: []
            })
                .overrideTemplate(TypeFeuillageComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TypeFeuillageComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TypeFeuillageService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new TypeFeuillage(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.typeFeuillages[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
