/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GatewayTestModule } from '../../../test.module';
import { EnsoleillementComponent } from 'app/entities/ensoleillement/ensoleillement.component';
import { EnsoleillementService } from 'app/entities/ensoleillement/ensoleillement.service';
import { Ensoleillement } from 'app/shared/model/ensoleillement.model';

describe('Component Tests', () => {
    describe('Ensoleillement Management Component', () => {
        let comp: EnsoleillementComponent;
        let fixture: ComponentFixture<EnsoleillementComponent>;
        let service: EnsoleillementService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [EnsoleillementComponent],
                providers: []
            })
                .overrideTemplate(EnsoleillementComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EnsoleillementComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EnsoleillementService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Ensoleillement(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.ensoleillements[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
