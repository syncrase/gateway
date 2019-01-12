/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GatewayTestModule } from '../../../test.module';
import { RichesseSolDetailComponent } from 'app/entities/richesse-sol/richesse-sol-detail.component';
import { RichesseSol } from 'app/shared/model/richesse-sol.model';

describe('Component Tests', () => {
    describe('RichesseSol Management Detail Component', () => {
        let comp: RichesseSolDetailComponent;
        let fixture: ComponentFixture<RichesseSolDetailComponent>;
        const route = ({ data: of({ richesseSol: new RichesseSol(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [RichesseSolDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RichesseSolDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RichesseSolDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.richesseSol).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
