/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GatewayTestModule } from '../../../test.module';
import { TypeTerreDetailComponent } from 'app/entities/type-terre/type-terre-detail.component';
import { TypeTerre } from 'app/shared/model/type-terre.model';

describe('Component Tests', () => {
    describe('TypeTerre Management Detail Component', () => {
        let comp: TypeTerreDetailComponent;
        let fixture: ComponentFixture<TypeTerreDetailComponent>;
        const route = ({ data: of({ typeTerre: new TypeTerre(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [TypeTerreDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TypeTerreDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TypeTerreDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.typeTerre).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
