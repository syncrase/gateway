/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { GatewayTestModule } from '../../../test.module';
import { TypeRacineDeleteDialogComponent } from 'app/entities/type-racine/type-racine-delete-dialog.component';
import { TypeRacineService } from 'app/entities/type-racine/type-racine.service';

describe('Component Tests', () => {
    describe('TypeRacine Management Delete Component', () => {
        let comp: TypeRacineDeleteDialogComponent;
        let fixture: ComponentFixture<TypeRacineDeleteDialogComponent>;
        let service: TypeRacineService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [TypeRacineDeleteDialogComponent]
            })
                .overrideTemplate(TypeRacineDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TypeRacineDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TypeRacineService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
