/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GatewayTestModule } from '../../../test.module';
import { StrateComponent } from 'app/entities/strate/strate.component';
import { StrateService } from 'app/entities/strate/strate.service';
import { Strate } from 'app/shared/model/strate.model';

describe('Component Tests', () => {
    describe('Strate Management Component', () => {
        let comp: StrateComponent;
        let fixture: ComponentFixture<StrateComponent>;
        let service: StrateService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [StrateComponent],
                providers: []
            })
                .overrideTemplate(StrateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(StrateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StrateService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Strate(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.strates[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
