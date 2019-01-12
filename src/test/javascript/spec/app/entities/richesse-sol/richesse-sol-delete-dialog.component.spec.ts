/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { GatewayTestModule } from '../../../test.module';
import { RichesseSolDeleteDialogComponent } from 'app/entities/richesse-sol/richesse-sol-delete-dialog.component';
import { RichesseSolService } from 'app/entities/richesse-sol/richesse-sol.service';

describe('Component Tests', () => {
    describe('RichesseSol Management Delete Component', () => {
        let comp: RichesseSolDeleteDialogComponent;
        let fixture: ComponentFixture<RichesseSolDeleteDialogComponent>;
        let service: RichesseSolService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [RichesseSolDeleteDialogComponent]
            })
                .overrideTemplate(RichesseSolDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RichesseSolDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RichesseSolService);
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
