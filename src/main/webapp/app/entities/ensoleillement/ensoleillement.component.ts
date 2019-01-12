import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IEnsoleillement } from 'app/shared/model/ensoleillement.model';
import { AccountService } from 'app/core';
import { EnsoleillementService } from './ensoleillement.service';

@Component({
    selector: 'jhi-ensoleillement',
    templateUrl: './ensoleillement.component.html'
})
export class EnsoleillementComponent implements OnInit, OnDestroy {
    ensoleillements: IEnsoleillement[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected ensoleillementService: EnsoleillementService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.ensoleillementService.query().subscribe(
            (res: HttpResponse<IEnsoleillement[]>) => {
                this.ensoleillements = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInEnsoleillements();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IEnsoleillement) {
        return item.id;
    }

    registerChangeInEnsoleillements() {
        this.eventSubscriber = this.eventManager.subscribe('ensoleillementListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
