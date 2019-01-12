import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITypeTerre } from 'app/shared/model/type-terre.model';
import { TypeTerreService } from './type-terre.service';

@Component({
    selector: 'jhi-type-terre-delete-dialog',
    templateUrl: './type-terre-delete-dialog.component.html'
})
export class TypeTerreDeleteDialogComponent {
    typeTerre: ITypeTerre;

    constructor(
        protected typeTerreService: TypeTerreService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.typeTerreService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'typeTerreListModification',
                content: 'Deleted an typeTerre'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-type-terre-delete-popup',
    template: ''
})
export class TypeTerreDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ typeTerre }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TypeTerreDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.typeTerre = typeTerre;
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
