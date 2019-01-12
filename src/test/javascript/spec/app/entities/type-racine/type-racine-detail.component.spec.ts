/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GatewayTestModule } from '../../../test.module';
import { TypeRacineDetailComponent } from 'app/entities/type-racine/type-racine-detail.component';
import { TypeRacine } from 'app/shared/model/type-racine.model';

describe('Component Tests', () => {
    describe('TypeRacine Management Detail Component', () => {
        let comp: TypeRacineDetailComponent;
        let fixture: ComponentFixture<TypeRacineDetailComponent>;
        const route = ({ data: of({ typeRacine: new TypeRacine(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [TypeRacineDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TypeRacineDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TypeRacineDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.typeRacine).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
