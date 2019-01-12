/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { GatewayTestModule } from '../../../test.module';
import { EnsoleillementDeleteDialogComponent } from 'app/entities/ensoleillement/ensoleillement-delete-dialog.component';
import { EnsoleillementService } from 'app/entities/ensoleillement/ensoleillement.service';

describe('Component Tests', () => {
    describe('Ensoleillement Management Delete Component', () => {
        let comp: EnsoleillementDeleteDialogComponent;
        let fixture: ComponentFixture<EnsoleillementDeleteDialogComponent>;
        let service: EnsoleillementService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [EnsoleillementDeleteDialogComponent]
            })
                .overrideTemplate(EnsoleillementDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EnsoleillementDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EnsoleillementService);
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
