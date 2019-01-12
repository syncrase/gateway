/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GatewayTestModule } from '../../../test.module';
import { RichesseSolComponent } from 'app/entities/richesse-sol/richesse-sol.component';
import { RichesseSolService } from 'app/entities/richesse-sol/richesse-sol.service';
import { RichesseSol } from 'app/shared/model/richesse-sol.model';

describe('Component Tests', () => {
    describe('RichesseSol Management Component', () => {
        let comp: RichesseSolComponent;
        let fixture: ComponentFixture<RichesseSolComponent>;
        let service: RichesseSolService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [RichesseSolComponent],
                providers: []
            })
                .overrideTemplate(RichesseSolComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RichesseSolComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RichesseSolService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new RichesseSol(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.richesseSols[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
