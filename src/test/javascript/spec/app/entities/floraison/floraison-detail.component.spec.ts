/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GatewayTestModule } from '../../../test.module';
import { FloraisonDetailComponent } from 'app/entities/floraison/floraison-detail.component';
import { Floraison } from 'app/shared/model/floraison.model';

describe('Component Tests', () => {
    describe('Floraison Management Detail Component', () => {
        let comp: FloraisonDetailComponent;
        let fixture: ComponentFixture<FloraisonDetailComponent>;
        const route = ({ data: of({ floraison: new Floraison(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [FloraisonDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(FloraisonDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(FloraisonDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.floraison).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
