/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GatewayTestModule } from '../../../test.module';
import { TypeTerreComponent } from 'app/entities/type-terre/type-terre.component';
import { TypeTerreService } from 'app/entities/type-terre/type-terre.service';
import { TypeTerre } from 'app/shared/model/type-terre.model';

describe('Component Tests', () => {
    describe('TypeTerre Management Component', () => {
        let comp: TypeTerreComponent;
        let fixture: ComponentFixture<TypeTerreComponent>;
        let service: TypeTerreService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [TypeTerreComponent],
                providers: []
            })
                .overrideTemplate(TypeTerreComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TypeTerreComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TypeTerreService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new TypeTerre(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.typeTerres[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
