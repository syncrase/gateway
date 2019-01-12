import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITypeFeuillage } from 'app/shared/model/type-feuillage.model';
import { TypeFeuillageService } from './type-feuillage.service';

@Component({
    selector: 'jhi-type-feuillage-delete-dialog',
    templateUrl: './type-feuillage-delete-dialog.component.html'
})
export class TypeFeuillageDeleteDialogComponent {
    typeFeuillage: ITypeFeuillage;

    constructor(
        protected typeFeuillageService: TypeFeuillageService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.typeFeuillageService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'typeFeuillageListModification',
                content: 'Deleted an typeFeuillage'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-type-feuillage-delete-popup',
    template: ''
})
export class TypeFeuillageDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ typeFeuillage }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TypeFeuillageDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.typeFeuillage = typeFeuillage;
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
