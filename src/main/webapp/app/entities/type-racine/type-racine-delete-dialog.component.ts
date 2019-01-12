import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITypeRacine } from 'app/shared/model/type-racine.model';
import { TypeRacineService } from './type-racine.service';

@Component({
    selector: 'jhi-type-racine-delete-dialog',
    templateUrl: './type-racine-delete-dialog.component.html'
})
export class TypeRacineDeleteDialogComponent {
    typeRacine: ITypeRacine;

    constructor(
        protected typeRacineService: TypeRacineService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.typeRacineService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'typeRacineListModification',
                content: 'Deleted an typeRacine'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-type-racine-delete-popup',
    template: ''
})
export class TypeRacineDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ typeRacine }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TypeRacineDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.typeRacine = typeRacine;
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
