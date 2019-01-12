import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRichesseSol } from 'app/shared/model/richesse-sol.model';
import { RichesseSolService } from './richesse-sol.service';

@Component({
    selector: 'jhi-richesse-sol-delete-dialog',
    templateUrl: './richesse-sol-delete-dialog.component.html'
})
export class RichesseSolDeleteDialogComponent {
    richesseSol: IRichesseSol;

    constructor(
        protected richesseSolService: RichesseSolService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.richesseSolService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'richesseSolListModification',
                content: 'Deleted an richesseSol'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-richesse-sol-delete-popup',
    template: ''
})
export class RichesseSolDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ richesseSol }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RichesseSolDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.richesseSol = richesseSol;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
