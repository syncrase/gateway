import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFloraison } from 'app/shared/model/floraison.model';
import { FloraisonService } from './floraison.service';

@Component({
    selector: 'jhi-floraison-delete-dialog',
    templateUrl: './floraison-delete-dialog.component.html'
})
export class FloraisonDeleteDialogComponent {
    floraison: IFloraison;

    constructor(
        protected floraisonService: FloraisonService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.floraisonService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'floraisonListModification',
                content: 'Deleted an floraison'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-floraison-delete-popup',
    template: ''
})
export class FloraisonDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ floraison }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(FloraisonDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.floraison = floraison;
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
