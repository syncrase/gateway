/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GatewayTestModule } from '../../../test.module';
import { RecolteDetailComponent } from 'app/entities/recolte/recolte-detail.component';
import { Recolte } from 'app/shared/model/recolte.model';

describe('Component Tests', () => {
    describe('Recolte Management Detail Component', () => {
        let comp: RecolteDetailComponent;
        let fixture: ComponentFixture<RecolteDetailComponent>;
        const route = ({ data: of({ recolte: new Recolte(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [RecolteDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RecolteDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RecolteDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.recolte).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
