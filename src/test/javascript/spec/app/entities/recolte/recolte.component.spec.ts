/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GatewayTestModule } from '../../../test.module';
import { RecolteComponent } from 'app/entities/recolte/recolte.component';
import { RecolteService } from 'app/entities/recolte/recolte.service';
import { Recolte } from 'app/shared/model/recolte.model';

describe('Component Tests', () => {
    describe('Recolte Management Component', () => {
        let comp: RecolteComponent;
        let fixture: ComponentFixture<RecolteComponent>;
        let service: RecolteService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [RecolteComponent],
                providers: []
            })
                .overrideTemplate(RecolteComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RecolteComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RecolteService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Recolte(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.recoltes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
