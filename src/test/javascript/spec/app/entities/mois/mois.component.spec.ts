/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GatewayTestModule } from '../../../test.module';
import { MoisComponent } from 'app/entities/mois/mois.component';
import { MoisService } from 'app/entities/mois/mois.service';
import { Mois } from 'app/shared/model/mois.model';

describe('Component Tests', () => {
    describe('Mois Management Component', () => {
        let comp: MoisComponent;
        let fixture: ComponentFixture<MoisComponent>;
        let service: MoisService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [MoisComponent],
                providers: []
            })
                .overrideTemplate(MoisComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(MoisComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MoisService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Mois(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.mois[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
